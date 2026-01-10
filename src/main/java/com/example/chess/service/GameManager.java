package com.example.chess.service;

import com.example.chess.model.game.GameState;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameManager {

    private final Map<String, GameState> games = new ConcurrentHashMap<>();


    public String createGame(){
        String id = UUID.randomUUID().toString();

        games.put(id, new GameState());
        return id;
    }

    public GameState getGameState(String id){
        return games.get(id);
    }
}
