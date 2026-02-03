package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;


public class Pawn extends Piece {

    private boolean enPassant = false;
    public Pawn(int r, int c, Color color) {
        super(r, c, color);
        setPath();

    }

    // do en passant later
    @Override
    public void findMyMoves(Piece[][] board){
        List<moveDto> moves = new ArrayList<>();

        int moveDirection =-1;
        if (color == Color.BLACK){moveDirection = 1;}

        //check if piece in front of pawn, if there is no piece, add coordinate
        if (board[row+moveDirection][column] == null) {
            moves.add(new moveDto(row + moveDirection, column));


            // piece hasnt move yet
            if (!hasMoved) {
                //check if there is piece 2 sqaures in front, if not, add coordinates
                if (board[row + (2 * moveDirection)][column] == null) {
                    moves.add(new moveDto(row + (2 * moveDirection), column));

                }
            }
        }

        //--------check right side-------
        //check if on edge
        if(column+1 <8) {
            //normal take
            //check look for piece to take
            if (board[row + moveDirection][column + 1] != null && board[row + moveDirection][column + 1].color != this.color) {
                moves.add(new moveDto(row + moveDirection, column + 1));
            }
            else {
                //en passant
                Piece side = board[row][column + 1];
                if (side instanceof Pawn && side.color != this.color && ((Pawn) side).enPassant) {
                    moves.add(new moveDto(row + moveDirection, column + 1));
                }
            }
        }

        //-------------check left side---------------
        //check if on edge
        if(column-1 >= 0) {
            //normal take
            //look for piece to take
            if (board[row + moveDirection][column - 1] != null && board[row + moveDirection][column - 1].color != this.color) {
                moves.add(new moveDto(row+moveDirection, column - 1));
            }

            else {
                //en passant
                Piece side = board[row][column - 1];
                if (side instanceof Pawn && side.color != this.color && ((Pawn) side).enPassant) {
                    moves.add(new moveDto(row + moveDirection, column - 1));
                }
            }
        }





        this.moves =moves;
    }

    @Override
    public boolean isValid(Piece[][] board ,int moveRow, int moveCol, int pieceX, int pieceY){
        enPassant = !hasMoved;


        return super.isValid(board, moveRow, moveCol, pieceX, pieceY);
    }

}
