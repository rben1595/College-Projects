package com.example.fypblackjacksecurity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {

    int itemId;
    String item_name;
    int item_cost;
    int qty;

}
