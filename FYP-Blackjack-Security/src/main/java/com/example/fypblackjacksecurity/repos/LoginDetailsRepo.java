package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDetailsRepo extends JpaRepository<LoginDetails, Integer> {
    Optional<LoginDetails> findByUsername(String username);
}
