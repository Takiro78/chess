package com.example.chess.controller;

import com.example.chess.ChessApplication;
import com.example.chess.service.PieceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    public final PieceService pieceService;
    public MainController(PieceService pieceService){
        this.pieceService = pieceService;
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/game")
    public String game(Model model) {
        model.addAttribute("pieces",pieceService.getAllPieces());
        return "game";
    }
}
