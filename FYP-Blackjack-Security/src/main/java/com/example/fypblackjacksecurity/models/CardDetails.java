package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card_details", schema = "FYP-DB", catalog = "")
public class CardDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "card_details_id")
    private int cardDetailsId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @Basic
    @Column(name = "card_number")
    private String cardNumber;
    @Basic
    @Column(name = "card_expiry_date")
    private String cardExpiryDate;
    @Basic
    @Column(name = "card_csv")
    private String cardCsv;
    @Basic
    @Column(name = "card_type")
    private String cardType;
    @Basic
    @Column(name = "store_details")
    private String storeDetails;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDetails that = (CardDetails) o;
        return cardDetailsId == that.cardDetailsId && userId == that.userId && cardNumber == that.cardNumber && cardCsv == that.cardCsv && Objects.equals(cardHolderName, that.cardHolderName) && Objects.equals(cardExpiryDate, that.cardExpiryDate) && Objects.equals(cardType, that.cardType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardDetailsId, userId, cardHolderName, cardNumber, cardExpiryDate, cardCsv, cardType);
    }
}
