package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payment_details", schema = "FYP-DB", catalog = "")
public class PaymentDetails implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payment_id")
    private int paymentId;

    @Basic
    @Column(name = "user_id")
    private int userId;

    @Basic
    @Column(name = "payment_amount")
    private int paymentAmount;
    @Basic
    @Column(name = "payment_date")
    private Timestamp paymentDate;
    @Basic
    @Column(name = "payment_type")
    private int paymentType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDetails that = (PaymentDetails) o;
        return paymentId == that.paymentId && userId == that.userId && paymentAmount == that.paymentAmount && paymentType == that.paymentType && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, userId, paymentAmount, paymentDate, paymentType);
    }
}
