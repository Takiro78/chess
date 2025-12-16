package com.example.chess.model.pieces;

import com.example.chess.model.Color;

public class Bishop extends Piece {
    public Bishop(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }
}
