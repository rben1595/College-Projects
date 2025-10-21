package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailsRepo extends JpaRepository<CardDetails, Integer> {
}
