
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
