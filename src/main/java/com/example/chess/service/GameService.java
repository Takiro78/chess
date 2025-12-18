package com.example.chess.service;

import com.example.chess.ChessApplication;
import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;
import com.example.chess.model.pieces.Piece;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    public Piece[][] board = new Piece[8][8];
    public Color turn =  Color.WHITE;

    public GameService() {
        for(Piece p : ChessApplication.pieces){
            board[p.getRow()][p.getColumn()] = p;
        }
    }

    public List<moveDto> getValidMoves(int row, int col){
        Piece searchPiece =  board[row][col];

        List<moveDto> validMoves = searchPiece.findMyMoves(board,row,col);
        return validMoves;
    }


}
