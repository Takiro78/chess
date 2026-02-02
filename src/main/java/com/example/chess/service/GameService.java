package com.example.chess.service;

import com.example.chess.ChessApplication;
import com.example.chess.dto.PieceDto;
import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;
import com.example.chess.model.pieces.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    public Piece[][] board = new Piece[8][8];
    public Color turn =  Color.WHITE;

    public ArrayList<Piece> Pieces = new ArrayList<>();
    
    
    public GameService() {

        Pieces.add(new Rook(0,0, Color.BLACK));
        Pieces.add(new Knight(0,1,Color.BLACK));
        Pieces.add(new Bishop(0,2,Color.BLACK));
        Pieces.add(new Queen(0,3,Color.BLACK));
        Pieces.add(new King(0,4,Color.BLACK));
        Pieces.add(new Bishop(0,5,Color.BLACK));
        Pieces.add(new Knight(0,6,Color.BLACK));
        Pieces.add(new Rook(0,7,Color.BLACK));

        Pieces.add(new Pawn(1,0,Color.BLACK));
        Pieces.add(new Pawn(1,1,Color.BLACK));
        Pieces.add(new Pawn(1,2,Color.BLACK));
        Pieces.add(new Pawn(1,3,Color.BLACK));
        Pieces.add(new Pawn(1,4,Color.BLACK));
        Pieces.add(new Pawn(1,5,Color.BLACK));
        Pieces.add(new Pawn(1,6,Color.BLACK));
        Pieces.add(new Pawn(1,7,Color.BLACK));





        Pieces.add(new Rook(7, 0, Color.WHITE));
        Pieces.add(new Knight(7, 1, Color.WHITE));
        Pieces.add(new Bishop(7, 2, Color.WHITE));
        Pieces.add(new Queen(4, 3, Color.WHITE));
        Pieces.add(new King(7, 4, Color.WHITE));
        Pieces.add(new Bishop(7, 5, Color.WHITE));
        Pieces.add(new Knight(7, 6, Color.WHITE));
        Pieces.add(new Rook(7, 7, Color.WHITE));

        Pieces.add(new Pawn(6, 0, Color.WHITE));
        Pieces.add(new Pawn(6, 1, Color.WHITE));
        Pieces.add(new Pawn(6, 2, Color.WHITE));
        Pieces.add(new Pawn(6, 3, Color.WHITE));
        Pieces.add(new Pawn(6, 4, Color.WHITE));
        Pieces.add(new Pawn(6, 5, Color.WHITE));
        Pieces.add(new Pawn(6, 6, Color.WHITE));
        Pieces.add(new Pawn(6, 7, Color.WHITE));
        
        for(Piece p : Pieces){
            board[p.getRow()][p.getColumn()] = p;
        }
    }

    public List<moveDto> getValidMoves(int row, int col){
        Piece searchPiece =  board[row][col];
        System.out.println(searchPiece);

        List<moveDto> validMoves = searchPiece.getMoves();
        return validMoves;
    }

    public boolean isValidAndMove(int pieceX,int pieceY,int moveX,int moveY){

        //find piece
        Piece pieceToMove = board[pieceX][pieceY];
        System.out.println("pieceToMove: " + pieceToMove);

        //find if coordinates are valid move
        boolean canMove = pieceToMove.isValid(board,moveX,moveY, pieceX,pieceY);

//        move piece
        if (canMove) {
            pieceToMove.setColumn(moveY);
            pieceToMove.setRow(moveX);
            board[moveX][moveY] = board[pieceX][pieceY];
            board[pieceX][pieceY] = null;
        }

        return canMove;
    }


    public ArrayList<PieceDto> getAllPieces(){
        ArrayList<PieceDto> pieceDTOs = new ArrayList<>();
        for (Piece[] row : board){
            for (Piece p : row) {
                if(p != null){
                pieceDTOs.add(createDTO(p));}
            }
        }
        return pieceDTOs;
    }

    public PieceDto createDTO(Piece piece){

        return new PieceDto(
                piece.getClass().getSimpleName(),
                piece.getColor(),
                piece.getImgPath(),
                piece.getRow(),
                piece.getColumn()
        );
    }

}
