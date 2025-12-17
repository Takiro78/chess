
console.log("board.js loaded");

document.addEventListener("DOMContentLoaded", () => {

    const squares = document.querySelectorAll(".square")
    let currentSquare=null;

    squares.forEach(square =>{
        square.addEventListener("click", () => {
            const row = square.dataset.row;
            const col = square.dataset.col;


            const piece = document.querySelector(`.piece.pos-${row}${col}`);

            if (piece){

                // if square already clicked, reset it
                if (currentSquare) {
                    currentSquare.classList.remove("selected");
                }

                //if a new square or unselected square clicked
                if (currentSquare != square) {
                    square.classList.add("selected");
                    currentSquare = square;
                }

                //clicking the same square and unselecting
                else{currentSquare=null;}
                // console.log("piece "+row+", "+col+" ");
            }
            else{
                console.log("nope"+row+col)
            }
        });
    });

    const pieces = document.querySelectorAll(".piece");
    console.log("dsflk");

    pieces.forEach(piece =>{
        console.log("hell0");
        piece.addEventListener("click", () =>{
            console.log("a click was noticed");
            const posClass = Array.from(piece.classList).find(cls =>cls.startsWith("pos-"));

            const square = document.querySelector(`.square.${posClass}`);



            // if square already clicked, reset it
            if (currentSquare) {
                currentSquare.classList.remove("selected");
            }

            //if a new square or unselected square clicked
            if (currentSquare != square) {
                square.classList.add("selected");
                currentSquare = square;
            }

            //clicking the same square and unselecting
            else{currentSquare=null;}
        });
    });
});

