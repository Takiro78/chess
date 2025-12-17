package com.example.chess.controller;

import com.example.chess.dto.moveDto;
import com.example.chess.service.GameService;
import com.example.chess.service.PieceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GameController {

    public final PieceService pieceService;
    public final GameService gameService;

    public GameController(PieceService pieceService, GameService gameService) {
        this.pieceService = pieceService;
        this.gameService = gameService;
    }

    @RequestMapping("/game")
    public String game(Model model) {
        model.addAttribute("pieces",pieceService.getAllPieces());
        return "game";
    }

    @ResponseBody
    @RequestMapping("/game/getMoves/{row}/{col}")
    public List<moveDto> getMoves(@PathVariable Integer row, @PathVariable Integer col) {
        System.out.println(row+" "+col);
        return gameService.getValidMoves(row,col);
    }

}
