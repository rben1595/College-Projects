package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "purchase_history", schema = "FYP-DB", catalog = "")
public class PurchaseHistory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchase_id")
    private int purchaseId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "order_total")
    private int orderTotal;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseHistory that = (PurchaseHistory) o;
        return purchaseId == that.purchaseId && userId == that.userId && orderTotal == that.orderTotal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, userId, orderTotal);
    }
}
