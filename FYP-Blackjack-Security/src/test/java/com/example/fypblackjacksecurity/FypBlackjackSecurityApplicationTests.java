package com.example.fypblackjacksecurity;

import com.example.fypblackjacksecurity.models.CardDetails;
import com.example.fypblackjacksecurity.validators.CardDetailsValidator;
import jakarta.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class FypBlackjackSecurityApplicationTests {

    @Test
    void validCardDetails() throws ParseException {

        //this test is to test for valid card details

        //create card details validator object
        CardDetailsValidator cardDetailsValidator = new CardDetailsValidator();

        //create card details object
        CardDetails cardDetails = new CardDetails(0, 0,
                "John Ryan", "4356667890894232",
                "2029-04-24", "232", "Mastercard", null);

        //run validator method
        boolean validDetails = cardDetailsValidator.cardValidator(cardDetails);

        //Assert that card details are valid
        Assertions.assertTrue(validDetails);

    }

    @Test
    void inValidCardDetails() throws ParseException {

        //this test is to test for invalid card details

        //create card details validator object
        CardDetailsValidator cardDetailsValidator = new CardDetailsValidator();

        //create card details object
        CardDetails cardDetails = new CardDetails(0, 0,
                "John Ryan", "4356667890894232",
                "2029-04-24", "453", "BankOfJim", null);

        //and invalid csv number has been added to his object
        //along with an invalid card type
        //the object inValidDetails should return false, thus the test should pass

        //run validator method
        boolean inValidDetails = cardDetailsValidator.cardValidator(cardDetails);

        //Assert that card details are valid
        Assertions.assertFalse(inValidDetails);

    }

}
