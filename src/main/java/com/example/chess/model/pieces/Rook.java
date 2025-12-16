package com.example.chess.model.pieces;

import com.example.chess.model.Color;

public class Rook extends Piece {

    public Rook(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }
}
