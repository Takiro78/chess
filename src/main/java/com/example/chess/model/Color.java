package com.example.chess.model;

public enum Color {
    WHITE,
    BLACK;

    public Color enemy(){return this == WHITE ? BLACK : WHITE;}
}
