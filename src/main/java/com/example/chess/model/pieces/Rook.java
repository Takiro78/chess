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
    public void findMyMoves(Piece[][] board){

        List<moveDto> validMoves = new ArrayList<>();
        for (int[] dir: directions){
            int moveRow = row + dir[0];
            int moveCol = column + dir[1];

            while(inbounds(moveRow, moveCol)){

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

        moves =validMoves;
    }
}
