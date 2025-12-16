package com.example.chess.dto;

import com.example.chess.model.Color;
import lombok.Getter;

public class PieceDto {
    @Getter
    private String type;
    @Getter
    private String color;
    @Getter
    private int row;
    @Getter
    private int col;
    @Getter
    private String imgPath;



    public PieceDto(String type, Color color, String imgPath, int row, int col) {
        this.type = type;
        this.color = color.name();
        this.row = row;
        this.col = col;
        this.imgPath = imgPath;
    }


}
