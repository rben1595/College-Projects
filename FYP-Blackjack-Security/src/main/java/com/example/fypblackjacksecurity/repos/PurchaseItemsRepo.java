package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.PurchasedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemsRepo extends JpaRepository<PurchasedItems, Integer> {
}
