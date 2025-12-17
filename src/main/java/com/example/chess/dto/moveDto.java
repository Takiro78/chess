package com.example.chess.dto;

public class moveDto {
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public moveDto(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
