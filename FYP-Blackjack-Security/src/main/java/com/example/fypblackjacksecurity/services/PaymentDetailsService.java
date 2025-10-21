package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.PaymentDetails;
import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.repos.PaymentDetailsRepo;
import com.example.fypblackjacksecurity.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepo paymentDetailsRepo;

    public PaymentDetailsService(PaymentDetailsRepo paymentDetailsRepo){
        this.paymentDetailsRepo = paymentDetailsRepo;
    }

//    public Optional<PaymentDetails> findUserByEmail(String email){
//        return paymentDetailsRepo.findUserByEmail(email);
//    }
//
//    public void saveUser(User user){
//        paymentDetailsRepo.save(user);
//    }
//
//    public List<User> findAll(){
//        return paymentDetailsRepo.findAll();
//    }

}
