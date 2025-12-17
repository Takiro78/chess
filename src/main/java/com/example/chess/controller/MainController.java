package com.example.chess.controller;

import com.example.chess.ChessApplication;
import com.example.chess.service.PieceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping("/index")
    public String index() {
        return "index";
    }


}
