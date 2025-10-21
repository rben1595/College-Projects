package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.LoginDetails;
import com.example.fypblackjacksecurity.models.PurchasedItems;
import com.example.fypblackjacksecurity.repos.PurchaseHistoryRepo;
import com.example.fypblackjacksecurity.repos.PurchaseItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseItemsService {

    @Autowired
    private PurchaseItemsRepo purchaseItemsRepo;

    public PurchaseItemsService(PurchaseItemsRepo purchaseItemsRepo) {
        this.purchaseItemsRepo = purchaseItemsRepo;
    }

    public void savePurchaseItems(PurchasedItems purchasedItem){
        purchaseItemsRepo.save(purchasedItem);
    }

}
