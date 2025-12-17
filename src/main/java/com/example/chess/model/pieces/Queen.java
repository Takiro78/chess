package com.example.chess.model.pieces;

import com.example.chess.model.Color;

public class Queen extends  Piece {
    public Queen(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

    public int[][] findMyMoves(Piece[][] board, int row, int col){
        return null;
    }
}

