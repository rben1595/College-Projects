package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchased_items", schema = "FYP-DB", catalog = "")
public class PurchasedItems {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchase_item_id")
    private int purchaseItemId;

    @Basic
    @Column(name = "purchase_history_id")
    private int purchaseHistoryId;

    @Basic
    @Column(name = "chip_type")
    private String chipType;
    @Basic
    @Column(name = "chip_price")
    private int chipPrice;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "sub_total")
    private int subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasedItems that = (PurchasedItems) o;
        return purchaseItemId == that.purchaseItemId && purchaseHistoryId == that.purchaseHistoryId && chipPrice == that.chipPrice && quantity == that.quantity && subTotal == that.subTotal && Objects.equals(chipType, that.chipType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseItemId, purchaseHistoryId, chipType, chipPrice, quantity, subTotal);
    }
}
