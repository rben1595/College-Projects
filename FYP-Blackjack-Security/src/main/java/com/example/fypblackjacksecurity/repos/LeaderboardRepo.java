package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardRepo extends JpaRepository<Leaderboard, Integer> {
}
