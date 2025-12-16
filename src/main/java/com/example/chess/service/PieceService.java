package com.example.chess.service;

import com.example.chess.ChessApplication;
import com.example.chess.dto.PieceDto;
import com.example.chess.model.pieces.Piece;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PieceService {

    public ArrayList<PieceDto> getAllPieces(){
        ArrayList<PieceDto> pieceDTOs = new ArrayList<>();
        for (Piece piece : ChessApplication.pieces){
            pieceDTOs.add(createDTO(piece));
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
