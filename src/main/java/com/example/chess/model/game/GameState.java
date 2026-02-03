package com.example.chess.model.game;

import com.example.chess.ChessApplication;
import com.example.chess.dto.PieceDto;
import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;
import com.example.chess.model.pieces.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameState {

    public Piece[][] board = new Piece[8][8];
    public Piece[][] tempBoard = new Piece[8][8];

    public Color turn =  Color.WHITE;

    private King bKing = new King(0,4,Color.BLACK);
    private King wKing = new King(7,4,Color.WHITE);

    public ArrayList<Piece> wPieces = new ArrayList<>();
    public ArrayList<Piece> bPieces = new ArrayList<>();


    public GameState() {
        bPieces.add(new Rook(0,0, Color.BLACK));
        bPieces.add(new Knight(0,1,Color.BLACK));
        bPieces.add(new Bishop(0,2,Color.BLACK));
        bPieces.add(new Queen(0,3,Color.BLACK));
        bPieces.add(bKing);
        bPieces.add(new Bishop(0,5,Color.BLACK));
        bPieces.add(new Knight(0,6,Color.BLACK));
        bPieces.add(new Rook(0,7,Color.BLACK));

        bPieces.add(new Pawn(1,0,Color.BLACK));
        bPieces.add(new Pawn(1,1,Color.BLACK));
        bPieces.add(new Pawn(1,2,Color.BLACK));
        bPieces.add(new Pawn(1,3,Color.BLACK));
        bPieces.add(new Pawn(1,4,Color.BLACK));
        bPieces.add(new Pawn(1,5,Color.BLACK));
        bPieces.add(new Pawn(1,6,Color.BLACK));
        bPieces.add(new Pawn(1,7,Color.BLACK));





        wPieces.add(new Rook(7, 0, Color.WHITE));
        wPieces.add(new Knight(7, 1, Color.WHITE));
        wPieces.add(new Bishop(7, 2, Color.WHITE));
        wPieces.add(new Queen(7, 3, Color.WHITE));
        wPieces.add(wKing);
        wPieces.add(new Bishop(7, 5, Color.WHITE));
        wPieces.add(new Knight(7, 6, Color.WHITE));
        wPieces.add(new Rook(7, 7, Color.WHITE));

        wPieces.add(new Pawn(6, 0, Color.WHITE));
        wPieces.add(new Pawn(6, 1, Color.WHITE));
        wPieces.add(new Pawn(6, 2, Color.WHITE));
        wPieces.add(new Pawn(6, 3, Color.WHITE));
        wPieces.add(new Pawn(6, 4, Color.WHITE));
        wPieces.add(new Pawn(6, 5, Color.WHITE));
        wPieces.add(new Pawn(6, 6, Color.WHITE));
        wPieces.add(new Pawn(6, 7, Color.WHITE));

        for(Piece p : wPieces){
            board[p.getRow()][p.getColumn()] = p;
            tempBoard[p.getRow()][p.getColumn()] = p;
        }

        for(Piece p : bPieces){
            board[p.getRow()][p.getColumn()] = p;
            tempBoard[p.getRow()][p.getColumn()] = p;
        }

        //tell each piece to find its current available moves

    }


    public List<moveDto> getValidMoves(int row, int col){
        Piece searchPiece =  board[row][col];
        System.out.println(searchPiece);
        searchPiece.findAndSetMoves(board, row, col);
//        List<moveDto> validMoves = searchPiece.getMoves();
        return searchPiece.getMoves();
    }

    public boolean isValidAndMove(int pieceX,int pieceY,int moveX,int moveY){

        //find piece
        Piece pieceToMove = board[pieceX][pieceY];
        System.out.println("pieceToMove: " + pieceToMove);

        //if piece not current turn
        if (pieceToMove.getColor() != this.turn){
            return false;
        }
        //find if coordinates are valid move
        boolean canMove = pieceToMove.isValid(board,moveX,moveY, pieceX,pieceY);

//        move piece
        if (canMove) {
            //check if piece is pawn, check if pawn moves diagnolly, check if destingation empty, if yes then en passant
            if (pieceToMove instanceof Pawn && moveY != pieceY && board[moveX][moveY] == null) {
//                Pieces.remove(board[pieceX][moveY]);
                board[pieceX][moveY] = null;

            }


            pieceToMove.setColumn(moveY);
            pieceToMove.setRow(moveX);
            board[moveX][moveY] = board[pieceX][pieceY];
//            Pieces.remove(board[pieceX][pieceY]);
            board[pieceX][pieceY] = null;
        }
        pieceToMove.findAndSetMoves(board,moveX,moveY);

        this.turn =(this.turn ==Color.WHITE) ? Color.BLACK : Color.WHITE;
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

    public boolean isKingInCheck(King king, Piece[][]board){
        ArrayList<Piece> enemy;
        if (king.getColor() ==Color.WHITE){ enemy = bPieces;}
        else{enemy = wPieces;}

        //loop trhough enemies
        for (Piece piece : enemy){
            List<moveDto> moves = piece.findMyMoves(board, piece.getRow(), piece.getColumn());
            for (moveDto m : moves){
                if (m.getCol() == piece.getColumn() && m.getRow() == piece.getRow()){
                    return true;
                }
            }
        }


        return false;
    }

}
