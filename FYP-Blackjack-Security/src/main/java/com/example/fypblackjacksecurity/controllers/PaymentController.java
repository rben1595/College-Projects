package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.*;
import com.example.fypblackjacksecurity.security.Authorization.PaymentAuthorization;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.security.PaymentToken;
import com.example.fypblackjacksecurity.services.*;
import com.example.fypblackjacksecurity.validators.CardDetailsValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired private SecurityKeyInfoService securityKeyInfoService;

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @Autowired
    private PurchaseItemsService purchaseItemsService;

    @Autowired
    private CardDetailsService cardDetailsService;

    @RequestMapping(value = "/checkOut", method = RequestMethod.GET)
    public ModelAndView checkOut(HttpSession session, Model model){

        return new ModelAndView("paymentPage", "cardDetails", new CardDetails());
    }

    @RequestMapping(value = "/processPayment", method = RequestMethod.POST)
    public ModelAndView gatherPaymentInfo(@ModelAttribute("cardDetails")
                                        CardDetails cardDetails, Authentication authentication, HttpSession session)
            throws ParseException, NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {

        //Payment Token Generation Class
        PaymentToken token = new PaymentToken();

        //validate card details
        CardDetailsValidator validator = new CardDetailsValidator();
        boolean isCardValid = validator.cardValidator(cardDetails);

        Token paymentToken = new Token();
        if(isCardValid){
            paymentToken = token.generatePaymentToken(authentication.getName());
        }

        //Once card information is validated & payment token is created - authorise payment & confirm order
        PaymentAuthorization paymentAuthorization = new PaymentAuthorization();

        //Get password, salt & ivParameterSpec from db
        Optional<SecurityKeyInfo> securityKeyInfoOptional = securityKeyInfoService.findById(1);
        //Change ivParameterSpec from string to array then to an IvParameterSpec variable
        String[] strArray = securityKeyInfoOptional.get().getIv_parameter_spec().split(",");
        byte[] byteArray = new byte[16];
        for (int i = 0; i < strArray.length; i++) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(byteArray);

        //Encryption - AES level encryption
        SecretKey key = EncryptionDecryptionConfig.getKeyFromPassword(securityKeyInfoOptional.get().getPassword(),securityKeyInfoOptional.get().getSalt());
        String algorithm = "AES/CBC/PKCS5Padding";

        Optional<User> authenticated_user = userService.findUserByEmail(EncryptionDecryptionConfig.encrypt(algorithm, authentication.getName(), key, ivParameterSpec));

        if(authenticated_user.isPresent()){
            boolean authorize_payment = paymentAuthorization.authorizePayment(isCardValid, paymentToken);
            boolean isOrderConfirmed = false;
            if(authorize_payment){
                System.out.println("Payment is authorized");
                isOrderConfirmed = paymentAuthorization.confirmOrder(cardDetails, authenticated_user.get().getUserId(), session, purchaseHistoryService, purchaseItemsService);
            }else{
                System.out.println("Payment is not authorized");
            }

            //lastly check to see if user wants there card details stored
            if(cardDetails.getStoreDetails().equals("on")){
                System.out.println("Store card details");
                cardDetails.setUserId(authenticated_user.get().getUserId());
                cardDetails.setStoreDetails("true");

                //encrypt card data before storing to db
                CardDetails encryptedCardInformation =
                        new CardDetails(0, authenticated_user.get().getUserId(),
                                EncryptionDecryptionConfig.encrypt(algorithm, cardDetails.getCardHolderName(), key, ivParameterSpec),
                                EncryptionDecryptionConfig.encrypt(algorithm, cardDetails.getCardNumber(), key, ivParameterSpec),
                                EncryptionDecryptionConfig.encrypt(algorithm, cardDetails.getCardExpiryDate(), key, ivParameterSpec),
                                EncryptionDecryptionConfig.encrypt(algorithm, cardDetails.getCardCsv(), key, ivParameterSpec),
                                EncryptionDecryptionConfig.encrypt(algorithm, cardDetails.getCardType(), key, ivParameterSpec),
                                cardDetails.getStoreDetails());

                cardDetailsService.saveCardDetails(encryptedCardInformation);

            }else{
                System.out.println("Don't save card details");
            }

            if(isOrderConfirmed){
                System.out.println("order is confirmed");
                return new ModelAndView("orderConfirmed", "successMessage", "Your order has been confirmed and placed, chips will be added to your account now.");
            }else{
                System.out.println("order is not confirmed");
                return new ModelAndView("error", "message", "Order has no been processed due to unforseen error. Please try again later.");
            }
        }else{
            return new ModelAndView("error", "message", "Order has no been processed due to unforseen error. Please try again later.");
        }
    }
}
