package com.example.fypblackjacksecurity.validators;

import com.example.fypblackjacksecurity.models.CardDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class CardDetailsValidator {

    public Boolean cardValidator(CardDetails cardDetails) throws ParseException {

        Boolean isCardValid = false;

        //check to see if card details are valid
        //#1 - Card Number
        boolean card_number = CardNumberValidator(cardDetails.getCardNumber());
        //#2 - CSV
        boolean csv = csvValidator(cardDetails.getCardNumber(), cardDetails.getCardCsv());
        //#3 - Expiry Date
        boolean card_expiry_date = expiryDateValidator(cardDetails.getCardExpiryDate());
        //#4 - Card Type
        boolean isCardTypeValid;
        if(cardDetails.getCardType().equals("Mastercard")){
            isCardTypeValid = true;
        }else if (cardDetails.getCardType().equals("American Express")){
            isCardTypeValid = true;
        }else if (cardDetails.getCardType().equals("Bank Of Ireland")){
            isCardTypeValid = true;
        }else if (cardDetails.getCardType().equals("Barclays")){
            isCardTypeValid = true;
        }else if (cardDetails.getCardType().equals("Visa")){
            isCardTypeValid = true;
        }else{
            isCardTypeValid = false;
        }
        //#6 - Card Holder Name
        boolean card_holder_name = cardHolderNameValidator(cardDetails.getCardHolderName());

        System.out.println("is card number valid: " + card_number);
        System.out.println("is csv valid: " + csv);
        System.out.println("is card expiry date valid: " + card_expiry_date);
        System.out.println("is card type valid: " + isCardTypeValid);
        System.out.println("is card holder name valid: " + card_holder_name);

        if(card_number && csv && card_expiry_date && isCardTypeValid && card_holder_name){
            isCardValid = true;
        }

        return isCardValid;
    }

    public Boolean CardNumberValidator(String card_number){

        boolean isCardNumberValid = false;

        int card_num_length = card_number.length();
        char[] card_digits = card_number.toCharArray();

        //valid digits
        String valid_digits = "0123456789";
        char[] valid_digits_array = valid_digits.toCharArray();

        int total_value = 0;

        for(char crd : card_digits){
            for(char val : valid_digits_array){
                if(crd == val){
                    total_value++;
                }
            }
        }

        if(card_num_length == 16 && total_value == 16){
            isCardNumberValid = true;
        }

        return isCardNumberValid;
    }

    public Boolean csvValidator(String card_number, String csv){
        return card_number.endsWith(csv);
    }

    public Boolean expiryDateValidator(String expiry_date) throws ParseException {

        boolean isExipryDateValid = false;

        System.out.println(expiry_date);

        Date currentDate = new Date();

        System.out.println(currentDate);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date expiryDate = formatter.parse(expiry_date);

        if(currentDate.before(expiryDate)){
            isExipryDateValid = true;
        }

        return isExipryDateValid;
    }

    public Boolean cardHolderNameValidator(String name){

        boolean isNameValid = false;

        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] valid_chars = validChars.toCharArray();

        //run check for spaces in string
        int total_num_of_whitespaces = 0;
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i) == ' '){
                total_num_of_whitespaces++;
            }
        }

        int total_value = 0;
        for (int i = 0; i < name.length(); i++) {
            for(char chars : valid_chars){
                if(name.charAt(i) == chars){
                    total_value++;
                }
            }
        }

        if(total_value == name.length() - total_num_of_whitespaces){
            isNameValid = true;
        }

        return isNameValid;
    }
}
