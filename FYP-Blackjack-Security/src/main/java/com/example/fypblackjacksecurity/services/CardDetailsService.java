package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.CardDetails;
import com.example.fypblackjacksecurity.models.PurchasedItems;
import com.example.fypblackjacksecurity.repos.CardDetailsRepo;
import com.example.fypblackjacksecurity.repos.PurchaseItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardDetailsService {

    @Autowired
    private CardDetailsRepo cardDetailsRepo;

    public CardDetailsService(CardDetailsRepo cardDetailsRepo) {
        this.cardDetailsRepo = cardDetailsRepo;
    }

    public void saveCardDetails(CardDetails cardDetails){
        cardDetailsRepo.save(cardDetails);
    }

}
