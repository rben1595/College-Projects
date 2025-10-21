package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.PaymentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDetailsRepo extends CrudRepository<PaymentDetails, Integer> {

}
