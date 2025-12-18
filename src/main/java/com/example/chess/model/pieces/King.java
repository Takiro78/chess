package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static final int[][] OFFSETS = new int[][]{
            {0,1},
            {0,-1},
            {-1,0},
            {-1,1},
            {-1,-1},
            {1,1},
            {1,-1},
            {1,0}
    };

    @Override
    public int[][] getOffsets() {
        return OFFSETS;
    }

    public King(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

}
