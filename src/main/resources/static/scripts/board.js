
console.log("board.js loaded");

document.addEventListener("DOMContentLoaded", () => {

    const squares = document.querySelectorAll(".square")
    let currentSquare=null;

    squares.forEach(square =>{
        square.addEventListener("click", () => {
            const row = square.dataset.row;
            const col = square.dataset.col;

            const piece = square.querySelector(".piece");
            if (piece){

                if (currentSquare) {
                    currentSquare.classList.remove("selected");
                }
                if (currentSquare != square) {
                    square.classList.add("selected");
                    currentSquare = square;
                }
                else{currentSquare=null;}
                console.log("piece "+row+", "+col+" ");
            }
            else{
                console.log("nope"+row+col)
            }
        });
    });

});

