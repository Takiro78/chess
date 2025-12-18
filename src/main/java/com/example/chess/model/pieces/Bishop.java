package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public int[][] directions = new int[][]{
            {1,-1},
            {1,1},
            {-1,-1},
            {-1,1}
    };
    public Bishop(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

    public List<moveDto> findMyMoves(Piece[][] board, int row, int col){

        List<moveDto> moves = new ArrayList<>();

        for(int[] dir: directions){

            int moveRow = row + dir[0];
            int moveCol = col + dir[1];

            while (inbounds(moveRow, moveCol)) {
                Piece piece = board[moveRow][moveCol];
                if(piece == null){
                    moves.add(new moveDto(moveRow, moveCol));

                    moveRow += dir[0];
                    moveCol += dir[1];
                }
                else{
                    if(piece.getColor() != color){moves.add(new moveDto(moveRow, moveCol));}
                    break;
                }


            }
        }
        return moves;
    }
}
