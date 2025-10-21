package com.example.fypblackjacksecurity.security.Authorization;

import com.example.fypblackjacksecurity.models.*;
import com.example.fypblackjacksecurity.security.PaymentToken;
import com.example.fypblackjacksecurity.services.PurchaseHistoryService;
import com.example.fypblackjacksecurity.services.PurchaseItemsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentAuthorization {

    public Boolean authorizePayment(boolean isCardValid, Token paymentToken){

        boolean authorized = false;

        //check card validation & make sure payment token is valid
        PaymentToken validateToken = new PaymentToken();

        boolean isTokenValid = validateToken.validateToken(paymentToken);

        System.out.println("Is card valid: " + isCardValid);
        System.out.println("Is token valid: " + isTokenValid);

        if(isCardValid && isTokenValid){
            authorized = true;
        }

        return authorized;
    }

    public Boolean confirmOrder(CardDetails cardDetails, int user_id, HttpSession session,
                                PurchaseHistoryService purchaseHistoryService, PurchaseItemsService purchaseItemsService){

        //create a new order & save to db
        PurchaseHistory newOrder = new PurchaseHistory();

        boolean orderConfirmation = false;

        //Get Session data for cart
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart != null){

            try{
                newOrder.setPurchaseId(0);
                newOrder.setUserId(user_id);
                newOrder.setOrderTotal(cart.getSub_tlt());

                //save order
                purchaseHistoryService.savePurchaseHistory(newOrder);

                List<PurchaseHistory> purchaseHistoryList = purchaseHistoryService.findAll();
                int purchaseHistoryId = purchaseHistoryList.get(purchaseHistoryList.size() - 1).getPurchaseId();

                //create object for purchased items
                for(CartItems crtItms : cart.getCartItemsList()){
                    PurchasedItems item =
                            new PurchasedItems(0, purchaseHistoryId, crtItms.getItem_name(),
                                    crtItms.getItem_cost(), crtItms.getQty(), (crtItms.getItem_cost() * crtItms.getQty()));

                    purchaseItemsService.savePurchaseItems(item);
                }
                orderConfirmation = true;
            }catch(Exception ex){
                System.out.println(ex);
            }
            return orderConfirmation;
        }else{
            return orderConfirmation;
        }
    }
}
