package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final int[][] OFFSETS = {
            { 2,  1},
            { 2, -1},
            {-2,  1},
            {-2, -1},
            { 1,  2},
            { 1, -2},
            {-1,  2},
            {-1, -2}
    };

    @Override
    public int[][] getOffsets() {
        return OFFSETS;
    }


    public Knight(int r, int c, Color color) {
        super(r, c, color);
        setPath();
    }

    @Override
    public String toString() {
        return "\uD83D\uDC34";
    }


}
