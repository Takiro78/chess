package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static final int[][] OFFSETS = new int[][]{
            {0,1},
            {0,-1},
            {-1,0},
            {-1,1},
            {-1,-1},
            {1,1},
            {1,-1},
            {1,0}
    };

    @Override
    public int[][] getOffsets() {
        return OFFSETS;
    }


    public List<moveDto>  findMyMoves(Piece[][] board, int row, int column){

        List<moveDto> validMoves  = super.findMyMoves(board, row, column);

        //check if castling is possible
        if (!hasMoved){

            //check if pieces on right side
            if (board[row][column+1]==null && board[row][column+2]==null){
                //no pieces in way
                //check if rook hasnt moved
                if (board[row][7] instanceof Rook && !board[row][7].hasMoved){
                    //can castle
                    validMoves.add( new moveDto(row,6));

                }

            }

            //check if pieces to the left
            if (board[row][column-1]==null && board[row][column-2] == null && board[row][column-3]==null){
                //no pieces in way
                //check if rook hasnt moved
                if (board[row][0] instanceof Rook && !board[row][0].hasMoved){
                    validMoves.add( new moveDto(row,2));
                }
            }


        }


        return validMoves;
    }



    public King(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

}
