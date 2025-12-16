package com.example.chess.model.pieces;

import com.example.chess.model.Color;

public class Queen extends  Piece {
    public Queen(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }
}

