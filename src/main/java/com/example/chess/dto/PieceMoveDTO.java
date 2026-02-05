package com.example.chess.dto;

import lombok.Getter;

public class PieceMoveDTO {

    @Getter
    private int pieceX;
    @Getter
    private int pieceY;


    @Getter
    private int moveX;
    @Getter
    private int moveY;
}
