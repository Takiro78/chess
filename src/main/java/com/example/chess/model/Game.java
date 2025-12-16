package com.example.chess.model;

import com.example.chess.ChessApplication;
import com.example.chess.model.pieces.Piece;

public class Game {

    public Piece[][] board = new Piece[8][8];
    public Color turn =  Color.WHITE;

    public Game(){
        for(Piece p : ChessApplication.pieces){
            board[p.getRow()][p.getColumn()] = p;
        }
    }
}
