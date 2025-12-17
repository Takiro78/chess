package com.example.chess.model.pieces;

import com.example.chess.model.Color;

public class Knight extends Piece {

    public Knight(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

    public int[][] findMyMoves(Piece[][] board, int row, int col){
        return null;
    }
}
