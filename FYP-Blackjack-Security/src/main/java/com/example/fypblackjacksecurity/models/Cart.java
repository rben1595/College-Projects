package com.example.fypblackjacksecurity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    int cartId;
    Date cartCreated;
    List<CartItems> cartItemsList;
    int sub_tlt;

}
