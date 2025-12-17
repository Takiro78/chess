package com.example.chess.service;

import com.example.chess.ChessApplication;
import com.example.chess.model.Color;
import com.example.chess.model.pieces.Piece;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public Piece[][] board = new Piece[8][8];
    public Color turn =  Color.WHITE;

    public GameService() {
        for(Piece p : ChessApplication.pieces){
            board[p.getRow()][p.getColumn()] = p;
        }
    }

    public int[][] getValidMoves(int row, int col){
        Piece searchPiece =  board[row][col];

        return null;
    }


}
