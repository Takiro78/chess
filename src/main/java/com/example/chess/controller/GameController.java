package com.example.chess.controller;

import com.example.chess.dto.PieceMoveDTO;
import com.example.chess.dto.moveDto;
import com.example.chess.model.game.GameState;
import com.example.chess.service.GameManager;
import com.example.chess.service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {

    //----------------------servive setup----------------------//
    @Autowired
    public final PieceService pieceService;
    @Autowired
    public final GameManager gameManager;

    public GameController(PieceService pieceService, GameManager gameManager) {
        this.pieceService = pieceService;
        this.gameManager = gameManager;
    }
    //---------------------------------------------------------//




    //----------------------------------open new game---------------//
    //create new game and redirect to page that has game id in url
    @RequestMapping("/game")
    public String createGame(Model model) {
        String id = gameManager.createGame();

        return "redirect:/game/" + id;
    }

    //helper method that allows url to have id and gives pieces to game
    @RequestMapping("/game/{gameId}")
    public String game(@PathVariable String gameId, Model model){
        GameState id = gameManager.getGameState(gameId);
        if(id == null){
            return "redirect:/game";
        }
        model.addAttribute("pieces",id.getAllPieces());
        return "game";
    }
    //---------------------------------------------------------------//




    @ResponseBody
    @RequestMapping("/game/{gameId}/getMoves/{row}/{col}")
    public List<moveDto> getMoves(@PathVariable String gameId, @PathVariable Integer row, @PathVariable Integer col) {
        System.out.println(row+" "+col);
        return gameManager.getGameState(gameId).getValidMoves(row,col);
//        return gameService.getValidMoves(row,col);
    }

    @ResponseBody
    @PostMapping("/game/{gameId}/isValidAndMove")
    public boolean isValidAndMove(@PathVariable String gameId,@RequestBody PieceMoveDTO move) {
        System.out.println("piece move initiated");
//        System.out.println(move.getPieceX()+" "+move.getPieceY());
        return gameManager.getGameState(gameId).isValidAndMove(move.getPieceX(),move.getPieceY(),move.getMoveX(),move.getMoveY());
//        return gameService.isValidAndMove(move.getPieceX(), move.getPieceY(), move.getMoveX(), move.getMoveY());

    }

}
