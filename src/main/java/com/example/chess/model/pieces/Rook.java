package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private final int[][] directions =  new int[][]{
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
    };

    public Rook(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }
    @Override
    public List<moveDto> findMyMoves(Piece[][] board,int row, int column){

        List<moveDto> validMoves = new ArrayList<>();
        //loop tthrough up down left and right
        for (int[] dir: directions){

            //adjacent square
            int moveRow = row + dir[0];
            int moveCol = column + dir[1];

            //while current square is on board
            while(inbounds(moveRow, moveCol)){

                //current square is empty
                if(board[moveRow][moveCol] == null){
                    validMoves.add(new moveDto(moveRow, moveCol));

                    moveRow = moveRow + dir[0];
                    moveCol = moveCol + dir[1];
                }
                else{
                    if(!isFriend(board[moveRow][moveCol])){
                        validMoves.add(new moveDto(moveRow, moveCol));
                    }
                    break;
                }
            }
        }

        return validMoves;
    }

    @Override
    public String toString() {
        return "🏰";
    }
}
