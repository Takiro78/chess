
console.log("board.js loaded");

document.addEventListener("DOMContentLoaded", () => {

    //all squares,
    const squares = document.querySelectorAll(".square")

    //current square selected, player wants to move piece on this square
    let currentSquare=null;

    let currentTurn="WHITE";


    //all pieces
    // const pieces = document.querySelectorAll(".piece");


    squares.forEach(square =>{

        square.addEventListener("click", () =>{
            //find position class for sqaure
            const posClass = Array.from(square.classList).find(cls =>cls.startsWith("pos-"));
            //
            const piece = document.querySelector(`.piece.${posClass}`);
            console.log("row:"+square.dataset.row+" col:"+square.dataset.col);

            //check if sqaure is canbemovedto

            if (square.classList.contains("canBeMovedTo") && currentSquare !=null){
                console.log("it can move here");
                isValidAndMove(currentSquare.dataset.row,currentSquare.dataset.col,square.dataset.row,square.dataset.col)

                //need server to check if valid
            }


            if(piece) {
                // if square already clicked, reset it
                if (currentSquare) {
                    currentSquare.classList.remove("selected");
                    console.log("hello");
                    document.querySelectorAll(".square.canBeMovedTo").forEach(sq => sq.classList.remove("canBeMovedTo"));
                }

                //if a new square or unselected square clicked
                if (currentSquare != square) {
                    square.classList.add("selected");
                    fetchAndShowMoves(square.dataset.row, square.dataset.col)
                    currentSquare = square;


                }

                //clicking the same square and unselecting
                else {
                    currentSquare = null;
                }
            }
        });
    });
});

async function fetchAndShowMoves(row,col){
    try{
        const response = await fetch(`/game/getMoves/${row}/${col}`);

        if(!response.ok){
            throw new Error("could not fetch moves");
        }

        const data = await response.json();
        console.log(data);
        data.forEach(({row,col}) =>{
            const square = document.querySelector(`.square[data-row="${row}"][data-col="${col}"]`)

            if(square){
                square.classList.add("canBeMovedTo");
            }
        });

    }
    catch(error){
        console.log(error);
    }
}

async function isValidAndMove(pieceX,pieceY,moveX,moveY){
    try{
        const response = await fetch(`/game/isValidAndMove`,{
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({pieceX,pieceY,moveX,moveY})
        });

        if (!response.ok){
            throw new Error("could not fetch if valid move")

        }

        const data = await response.json();
        console.log(data);
        if (data){
            move(pieceX,pieceY,moveX,moveY);
        }
    }
    catch(error){
        console.log(error);
    }
}
function move(pieceX,pieceY,moveX,moveY){

    console.log(`piece.pos-${pieceX}${pieceY}`);

    //find piece to move
    const pieceToMove = document.querySelector(`.piece.pos-${pieceX}${pieceY}`);


    //check for piece to remove
    const enemy = document.querySelector(`.piece.pos-${moveX}${moveY}`);
    if (enemy){
        enemy.remove();

    }

    //undo selected ui stuff
    document.querySelector(`.selected`).classList.remove("selected")
    document.querySelectorAll(".square.canBeMovedTo").forEach(sq => sq.classList.remove("canBeMovedTo"));

    // console.log(pieceToMove);


    pieceToMove.classList.remove(`pos-${pieceX}${pieceY}`);
    pieceToMove.classList.add(`pos-${moveX}${moveY}`);

}
