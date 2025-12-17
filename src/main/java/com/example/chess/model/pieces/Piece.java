package com.example.chess.model.pieces;

import com.example.chess.model.Color;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class Piece {
    @Getter
    @Setter
    protected int row;
    @Getter
    @Setter
    protected int column;
    @Getter
    protected final Color color;
    @Getter
    protected String imgPath;



    public Piece(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public void setPath(){
        imgPath = "/pieces/pixel_chess/pieces/"+color.toString()+"_"+this.getClass().getSimpleName()+".png";
    }

    public abstract int[][] findMyMoves(Piece[][] board, int row, int col);


}
