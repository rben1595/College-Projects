package com.example.fypblackjacksecurity.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterModel {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String country;

    private String postCode;

    private String username;

    private String password;

}
