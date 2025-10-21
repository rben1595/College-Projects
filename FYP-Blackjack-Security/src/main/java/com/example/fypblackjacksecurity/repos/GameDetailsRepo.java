package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDetailsRepo extends JpaRepository<GameDetails, Integer> {
}
