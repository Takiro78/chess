package com.example.chess.model.game;

import com.example.chess.dto.PieceDto;
import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;
import com.example.chess.model.pieces.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameState {

    private Piece[][] board = new Piece[8][8];
    public Piece[][] tempBoard = new Piece[8][8];

    public Color turn = Color.WHITE;

    private final King bKing = new King(0, 4, Color.BLACK);
    private final King wKing = new King(7, 4, Color.WHITE);

    public ArrayList<Piece> wPieces = new ArrayList<>();
    public ArrayList<Piece> bPieces = new ArrayList<>();


    public GameState() {
        bPieces.add(new Rook(0, 0, Color.BLACK));
        bPieces.add(new Knight(0, 1, Color.BLACK));
        bPieces.add(new Bishop(0, 2, Color.BLACK));
        bPieces.add(new Queen(0, 3, Color.BLACK));
        bPieces.add(bKing);
        bPieces.add(new Bishop(0, 5, Color.BLACK));
        bPieces.add(new Knight(0, 6, Color.BLACK));
        bPieces.add(new Rook(0, 7, Color.BLACK));

        bPieces.add(new Pawn(1, 0, Color.BLACK));
        bPieces.add(new Pawn(1, 1, Color.BLACK));
        bPieces.add(new Pawn(1, 2, Color.BLACK));
        bPieces.add(new Pawn(1, 3, Color.BLACK));
        bPieces.add(new Pawn(1, 4, Color.BLACK));
        bPieces.add(new Pawn(1, 5, Color.BLACK));
        bPieces.add(new Pawn(1, 6, Color.BLACK));
        bPieces.add(new Pawn(1, 7, Color.BLACK));


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


        //intialize boards
        for (Piece p : wPieces) {
            board[p.getRow()][p.getColumn()] = p;
            tempBoard[p.getRow()][p.getColumn()] = p;
        }

        for (Piece p : bPieces) {
            board[p.getRow()][p.getColumn()] = p;
            tempBoard[p.getRow()][p.getColumn()] = p;
        }


    }


    public List<moveDto> getValidMoves(int row, int col) {
        Piece searchPiece = board[row][col];
        System.out.println(searchPiece);

        //stop player from looking at moves when not their turn
        if (searchPiece.getColor() != turn) {
            return null;
        }

        //get valid moves
        List<moveDto> pMoves = searchPiece.findMyMoves(board, row, col);
        //simulate moves
        searchPiece.setMoves(simulateMoves(searchPiece, pMoves));

//        List<moveDto> validMoves = searchPiece.getMoves();
        return searchPiece.getMoves();
    }

    public boolean isValidAndMove(int pieceX, int pieceY, int moveX, int moveY) {

        //find piece
        Piece pieceToMove = board[pieceX][pieceY];
        System.out.println("pieceToMove: " + pieceToMove);

        //if piece not current turn
        if (pieceToMove.getColor() != this.turn) {
            return false;
        }
        //find if coordinates are valid move
        boolean canMove = pieceToMove.isValid(board, moveX, moveY, pieceX, pieceY);

//        move piece
        if (canMove) {
            //check if piece is pawn, check if pawn moves diagnolly, check if destingation empty, if yes then en passant
            if (pieceToMove instanceof Pawn && moveY != pieceY && board[moveX][moveY] == null) {
//                Pieces.remove(board[pieceX][moveY]);
                board[pieceX][moveY] = null;

            }

            if (pieceToMove instanceof King) {

                if (moveY == pieceY + 2) {
                    // move rook from h-file to f-file
                    Piece rook = board[pieceX][7];
                    board[pieceX][5] = rook;
                    rook.setColumn(5);
                    board[pieceX][7] = null;
                }

                // queen side castle (white: e1 -> c1, black: e8 -> c8)
                else if (moveY == pieceY - 2) {
                    // move rook from a-file to d-file
                    Piece rook = board[pieceX][0];
                    board[pieceX][3] = rook;
                    rook.setColumn(3);
                    board[pieceX][0] = null;
                }

            }


            pieceToMove.setColumn(moveY);
            pieceToMove.setRow(moveX);
            board[moveX][moveY] = board[pieceX][pieceY];
//            Pieces.remove(board[pieceX][pieceY]);
            board[pieceX][pieceY] = null;

            printBoard(tempBoard);
        }
        pieceToMove.findMyMoves(board, moveX, moveY);

        this.turn = (this.turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
//        if (isCheckmate((this.turn == Color.WHITE) ? wPieces : bPieces)) {
//            System.out.println(turn + " is in checkmate");
//        }
        return canMove;
    }


    public ArrayList<PieceDto> getAllPieces() {
        ArrayList<PieceDto> pieceDTOs = new ArrayList<>();
        for (Piece[] row : board) {
            for (Piece p : row) {
                if (p != null) {
                    pieceDTOs.add(createDTO(p));
                }
            }
        }
        return pieceDTOs;
    }

    public PieceDto createDTO(Piece piece) {

        return new PieceDto(
                piece.getClass().getSimpleName(),
                piece.getColor(),
                piece.getImgPath(),
                piece.getRow(),
                piece.getColumn()
        );
    }

    //checks if given king is in check in given board
    public boolean isKingInCheck(King king, Piece[][] board) {

        //get enemy pieces
        ArrayList<Piece> enemy = (king.getColor() == Color.WHITE) ? bPieces : wPieces;


        //loop trhough enemies
        for (Piece piece : enemy) {


            int row = piece.getRow();
            int column = piece.getColumn();
            //prevent iterating over pieces not on board anymore
            if (board[row][column] != piece) {
                continue;
            }


            //get moves for current piece
            List<moveDto> moves = piece.findMyMoves(board, piece.getRow(), piece.getColumn());

            //check moves to see if enemy can now take friendly king
            for (moveDto m : moves) {
                if (m.getCol() == king.getColumn() && m.getRow() == king.getRow()) {
                    return true;
                }
            }
        }


        return false;
    }

    public void resetTempBoard() {
    tempBoard = new Piece[8][8];
    for (int i = 0; i < 8; i++) {
        tempBoard[i] = Arrays.copyOf(board[i], 8);
    }

    }


    public List<moveDto> simulateMoves(Piece p, List<moveDto> moves) {
        int row = p.getRow();
        int col = p.getColumn();
//        System.out.println("before moves: " + moves);

        //find out who is friendly king for check check
        King king = (p.getColor() == Color.WHITE) ? wKing : bKing;

        //loop through current pieces possible moves to simulate
        for (int i = moves.size() - 1; i >= 0; i--) {
            int moveRow = moves.get(i).getRow();
            int moveCol = moves.get(i).getCol();

//            System.out.println("move row: " + moveRow + " col: " + moveCol);
//            System.out.println(p.getRow() + "pRow " + p.getColumn() + "col " );

            //simulate move
            tempBoard[moveRow][moveCol] = p;
//            p.setRow(moveRow);
//            p.setColumn(moveCol);
            tempBoard[row][col] = null;

            //check check



            if (isKingInCheck(king, tempBoard)) {
                moves.remove(i);
//                System.out.println("this setup causes check");
            }

//           printBoard(tempBoard);


            //reset piece's position
//            p.setRow(row);
//            p.setColumn(col);

            //reset board
            resetTempBoard();


        }


//        System.out.println("after moves: " + moves);
        return moves;
    }

    public boolean isCheckmate(ArrayList<Piece> pieces) {


        for (Piece searchPiece : pieces) {

            //get valid moves
            List<moveDto> pMoves = searchPiece.findMyMoves(board, searchPiece.getRow(), searchPiece.getColumn());
            //simulate moves

            if (simulateMoves(searchPiece, pMoves).isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public void printBoard(Piece[][] board) {


        System.out.println("#############################");


        for (Piece[] row : board) {
            for (Piece p : row) {
                if (p == null) {
                    System.out.print("⬜"+" ");
                } else {
                    System.out.print(p + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("#############################");

    }
}

