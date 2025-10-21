package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.GameDetails;
import com.example.fypblackjacksecurity.models.LoginDetails;
import com.example.fypblackjacksecurity.repos.GameDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameDetailsService {

    @Autowired
    private GameDetailsRepo gameDetailsRepo;

    public GameDetailsService(GameDetailsRepo gameDetailsRepo) {
        this.gameDetailsRepo = gameDetailsRepo;
    }

    public void saveGameDetailsToDb(GameDetails gameDetails){
        gameDetailsRepo.save(gameDetails);
    }

    public List<GameDetails> findAll() { return gameDetailsRepo.findAll(); }
}
