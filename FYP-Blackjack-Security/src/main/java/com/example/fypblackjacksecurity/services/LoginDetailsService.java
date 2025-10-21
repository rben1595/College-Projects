package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.LoginDetails;
import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.repos.LoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginDetailsService {
    @Autowired
    private LoginDetailsRepo loginDetailsRepo;

    public LoginDetailsService(LoginDetailsRepo loginDetailsRepo) {
        this.loginDetailsRepo = loginDetailsRepo;
    }

    public void saveLoginDetails(LoginDetails loginDetails){
        loginDetailsRepo.save(loginDetails);
    }

    public Optional<LoginDetails> findByUsername(String username){
         return loginDetailsRepo.findByUsername(username);
    }

    public void deleteLoginDetails(LoginDetails loginDetails) {
        loginDetailsRepo.delete(loginDetails);
    }
}
