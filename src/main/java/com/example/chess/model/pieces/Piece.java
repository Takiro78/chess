package com.example.chess.model.pieces;

import com.example.chess.dto.moveDto;
import com.example.chess.model.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//import java.awt.*;

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

    @Getter
    protected List<moveDto> moves;

    protected boolean hasMoved = false;


    protected  int[][] getOffsets(){
        return new int[][]{};
    }



    public Piece(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        this.color = color;
    }

    public void setPath(){
        imgPath = "/pieces/pixel_chess/pieces/"+color.toString()+"_"+this.getClass().getSimpleName()+".png";
    }

    public List<moveDto>  findMyMoves(Piece[][] board, int row, int column){
        List<moveDto> validMoves = new ArrayList<>();

        for (int[] offset: getOffsets()){
            int  moveRow = row + offset[0];
            int moveCol = column + offset[1];

            if (inbounds(moveRow, moveCol)){
                if (isFriend(board[moveRow][moveCol])){
                    continue;
                }

                validMoves.add(new moveDto(moveRow, moveCol));

            }
        }
        System.out.println(validMoves);
        return validMoves;
    }

    public void findAndSetMoves(Piece[][] board, int row, int column){
        this.moves = findMyMoves(board, row, column);
    }

    public boolean inbounds(int moveRow, int moveCol) {
        return moveRow >= 0 && moveRow < 8 && moveCol >= 0 && moveCol < 8;
    }

    public boolean isFriend(Piece piece){
        if (piece ==null){
            return false;
        }
        return piece.color == this.color;
    }

    public boolean isValid(Piece[][] board ,int moveRow, int moveCol, int pieceX, int pieceY){

        hasMoved = true;

        for (moveDto move: moves){
//            System.out.println(move.toString());
            if (move.getRow() == moveRow && move.getCol() == moveCol){
                return true;
            }
        }
        return false;
    }

}
