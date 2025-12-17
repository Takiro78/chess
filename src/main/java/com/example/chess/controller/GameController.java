package com.example.chess.controller;

import com.example.chess.service.GameService;
import com.example.chess.service.PieceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
