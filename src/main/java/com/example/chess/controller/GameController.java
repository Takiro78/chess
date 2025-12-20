package com.example.chess.controller;

import com.example.chess.dto.doubleMoveDto;
import com.example.chess.dto.moveDto;
import com.example.chess.service.GameService;
import com.example.chess.service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    public final PieceService pieceService;
    @Autowired
    public final GameService gameService;

    public GameController(PieceService pieceService, GameService gameService) {
        this.pieceService = pieceService;
        this.gameService = gameService;
    }

    @RequestMapping("/game")
    public String game(Model model) {
        model.addAttribute("pieces",gameService.getAllPieces());
        return "game";
    }

    @ResponseBody
    @RequestMapping("/game/getMoves/{row}/{col}")
    public List<moveDto> getMoves(@PathVariable Integer row, @PathVariable Integer col) {
        System.out.println(row+" "+col);
        return gameService.getValidMoves(row,col);
    }

    @ResponseBody
    @PostMapping("/game/isValidAndMove")
    public boolean isValidAndMove(@RequestBody doubleMoveDto move) {
        System.out.println("piece move initiated");
//        System.out.println(move.getPieceX()+" "+move.getPieceY());
        return gameService.isValidAndMove(move.getPieceX(), move.getPieceY(), move.getMoveX(), move.getMoveY());

    }

}
