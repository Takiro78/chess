package com.example.chess.model.pieces;

import com.example.chess.model.Color;

public class King extends Piece {
    public King(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }
}
