package com.example.chess;

import com.example.chess.model.Color;
import com.example.chess.model.pieces.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ChessApplication {

    public static ArrayList<Piece> pieces = new ArrayList<>();



    public static void main(String[] args) {

        pieces.add(new Rook(0,0, Color.BLACK));
        pieces.add(new Knight(0,1,Color.BLACK));
        pieces.add(new Bishop(0,2,Color.BLACK));
        pieces.add(new Queen(0,3,Color.BLACK));
        pieces.add(new King(0,4,Color.BLACK));
        pieces.add(new Bishop(0,5,Color.BLACK));
        pieces.add(new Knight(0,6,Color.BLACK));
        pieces.add(new Rook(0,7,Color.BLACK));

        pieces.add(new Pawn(1,0,Color.BLACK));
        pieces.add(new Pawn(1,1,Color.BLACK));
        pieces.add(new Pawn(1,2,Color.BLACK));
        pieces.add(new Pawn(1,3,Color.BLACK));
        pieces.add(new Pawn(1,4,Color.BLACK));
        pieces.add(new Pawn(1,5,Color.BLACK));
        pieces.add(new Pawn(1,6,Color.BLACK));
        pieces.add(new Pawn(1,7,Color.BLACK));





        pieces.add(new Rook(7, 0, Color.WHITE));
        pieces.add(new Knight(7, 1, Color.WHITE));
        pieces.add(new Bishop(7, 2, Color.WHITE));
        pieces.add(new Queen(4, 3, Color.WHITE));
        pieces.add(new King(7, 4, Color.WHITE));
        pieces.add(new Bishop(7, 5, Color.WHITE));
        pieces.add(new Knight(7, 6, Color.WHITE));
        pieces.add(new Rook(7, 7, Color.WHITE));

        pieces.add(new Pawn(6, 0, Color.WHITE));
        pieces.add(new Pawn(6, 1, Color.WHITE));
        pieces.add(new Pawn(6, 2, Color.WHITE));
        pieces.add(new Pawn(6, 3, Color.WHITE));
        pieces.add(new Pawn(6, 4, Color.WHITE));
        pieces.add(new Pawn(6, 5, Color.WHITE));
        pieces.add(new Pawn(6, 6, Color.WHITE));
        pieces.add(new Pawn(6, 7, Color.WHITE));

        SpringApplication.run(ChessApplication.class, args);
    }

}
