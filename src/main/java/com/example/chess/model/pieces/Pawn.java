package com.example.chess.model.pieces;

import com.example.chess.model.Color;


public class Pawn extends Piece {


    public Pawn(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }
}
