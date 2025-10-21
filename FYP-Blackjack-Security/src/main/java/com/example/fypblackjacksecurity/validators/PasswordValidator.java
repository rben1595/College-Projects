package com.example.fypblackjacksecurity.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        boolean isValidPassword = false;

        //Create Validator for password
        if(password.isEmpty()){
            return false;
        }else{
            //Check to see does password meet the criteria
            //1 special character
            //1 uppercase
            //1 number
            //length between 7 - 14 chars

            char[] passwordArr;
            passwordArr = password.toCharArray();

            //Create loop to iterate through password characters to check if number is used
            boolean numUsed = false;
            int num = 0;
            for (char value : passwordArr) {
                if (value == num) {
                    numUsed = true;
                } else {
                    num++;
                }
            }

            //Create loop to check if uppercase char is used
            boolean uppercaseUsed = false;
            for (char c : passwordArr) {
                String passwordChar = String.valueOf(c);
                if (passwordChar.equals(passwordChar.toUpperCase())) {
                    uppercaseUsed = true;
                }
            }

            //Create loop to see if special char is used
            char[] specialArr = {'!', '@', '€', '£', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '[', ']', '{', '}', ';', ':', '"', '|', ',', '<', '.', '>', '/', '?', '~', '~'};

            boolean specialCharUsed = false;
            int charNum = 0;
            for (int i = 0; i < passwordArr.length; i++) {
                if(passwordArr[i] == specialArr[charNum]){
                    specialCharUsed = true;
                }else{
                    charNum++;
                }
            }

            if(numUsed && uppercaseUsed && specialCharUsed){
                isValidPassword = true;
            }

            System.out.println(isValidPassword);
        }
        return isValidPassword;
    }
}
