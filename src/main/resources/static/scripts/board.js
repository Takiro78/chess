
console.log("board.js loaded");

document.addEventListener("DOMContentLoaded", () => {

    const squares = document.querySelectorAll(".square")
    let currentSquare=null;



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
                fetchMoves(square.dataset.row,square.dataset.col)
                currentSquare = square;
            }

            //clicking the same square and unselecting
            else{currentSquare=null;}
        });
    });
});

async function fetchMoves(row,col){
    try{
        const response = await fetch(`/game/getMoves/${row}/${col}`);

        if(!response.ok){
            throw new Error("could not fetch moves");
        }

        const data = await response.json();
        console.log(data);
    }
    catch(error){
        console.log(error);
    }
}
