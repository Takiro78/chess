package com.example.chess.model.pieces;

import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;


public class Pawn extends Piece {

    private boolean hasMoved = false;
    public Pawn(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

    // do en passant later
    @Override
    public int[][] findMyMoves(Piece[][] board, int row, int col){
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();

        int moveDirection =1;
        if (color == Color.BLACK){moveDirection = -1;}

        //check if piece in front of pawn, if there is no piece, add coordinate
        if (board[row+moveDirection][col] == null){
            moves.add(new ArrayList<>(List.of(row+moveDirection,col)));

        }

        //check right take
        if (board[row+moveDirection][col+1] !=null && board[row+moveDirection][col+1].color !=this.color ){
            moves.add(new ArrayList<>(List.of(row+moveDirection,col+1)));
        }


        //check left take
        if (board[row+moveDirection][col-1] !=null && board[row+moveDirection][col-1].color !=this.color ){
            moves.add(new ArrayList<>(List.of(row+moveDirection,col-1)));
        }

        return null;
    }


}
