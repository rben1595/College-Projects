package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.PurchaseHistory;
import com.example.fypblackjacksecurity.models.PurchasedItems;
import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.repos.LoginDetailsRepo;
import com.example.fypblackjacksecurity.repos.PurchaseHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService {
    @Autowired
    private PurchaseHistoryRepo purchaseHistoryRepo;

    public PurchaseHistoryService(PurchaseHistoryRepo purchaseHistoryRepo) {
        this.purchaseHistoryRepo = purchaseHistoryRepo;
    }

    public void savePurchaseHistory(PurchaseHistory purchaseHistory){
        purchaseHistoryRepo.save(purchaseHistory);
    }

    public List<PurchaseHistory> findAll(){
        return purchaseHistoryRepo.findAll();
    }
}
