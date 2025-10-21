package com.example.fypblackjacksecurity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private int tokenId;
    private Date dateIssued;
    private Date expiryDate;
    private String subject;
    private String signature;

}
