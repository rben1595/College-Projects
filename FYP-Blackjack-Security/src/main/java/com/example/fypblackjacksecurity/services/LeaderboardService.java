package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.Leaderboard;
import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.repos.GameDetailsRepo;
import com.example.fypblackjacksecurity.repos.LeaderboardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
    @Autowired
    private LeaderboardRepo leaderboardRepo;


    public LeaderboardService(LeaderboardRepo leaderboardRepo) {
        this.leaderboardRepo = leaderboardRepo;
    }

    public List<Leaderboard> findAll(){
        return leaderboardRepo.findAll();
    }
}
