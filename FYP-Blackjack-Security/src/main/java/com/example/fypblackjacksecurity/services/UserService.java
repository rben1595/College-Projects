package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findUserByEmail(String email){
        return userRepo.findUserByEmail(email);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public void deleteUser(User user) {userRepo.delete(user);}
}
