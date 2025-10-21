package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.*;
import com.example.fypblackjacksecurity.repos.LeaderboardRepo;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Controller
public class BaseController {

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Autowired
    private SecurityKeyInfoService securityKeyInfoService;

    @Autowired
    private GameDetailsService gameDetailsService;

    @GetMapping("/")
    public String greeting(HttpSession session) {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/leaderboard")
    public String leaderboard(HttpSession session, Model model, Authentication authentication)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        //Get Game Details information
        List<GameDetails> gameData = gameDetailsService.findAll();
        List<Leaderboard> leaderboardList = getLeaderboardData(gameData, authentication);

        //create new model attribute
        model.addAttribute("leaderboardList", leaderboardList);

        return "leaderboard";

//        String page;
//
//        try{
//            //Get Game Details information
//            List<GameDetails> gameData = gameDetailsService.findAll();
//            List<Leaderboard> leaderboardList = getLeaderboardData(gameData, authentication);
//
//            //create new model attribute
//            model.addAttribute("leaderboardList", leaderboardList);
//
//            return "leaderboard";
//        }catch (Exception exception){
//            page = "error";
//        }
//
//        return page;
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(Authentication authentication){

        String page;

        try{
            //method to delete account
            if(authentication.isAuthenticated()){

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

                String encryptedUsername = EncryptionDecryptionConfig.encrypt(algorithm, authentication.getName(), key, ivParameterSpec);

                //first find user - next delete user
                userService.deleteUser(userService.findUserByEmail(encryptedUsername).get());

                //delete the login details of the user
                loginDetailsService.deleteLoginDetails(loginDetailsService.findByUsername(authentication.getName()).get());

                page = "redirect:/logout";

            }else{
                page = "error";
            }
        }catch(Exception exception){

            System.out.println(exception);

            page = "error";
        }

        return page;
    }

    @GetMapping("/store")
    public String store() {
        return "store";
    }

    @GetMapping("/addToCart/{price}")
    public ModelAndView addToCart(@PathVariable int price, HttpSession session){

        Cart cart;
        if(session.getAttribute("cart") == null){
            //create a new cart item object & cart object
            cart = new Cart(1, new Date(), new ArrayList<CartItems>(), 0);
        }else{
            cart = (Cart) session.getAttribute("cart");
        }

        CartItems item = new CartItems();

        if(price == 100){
            item.setItem_cost(100);
            item.setQty(1);
            item.setItem_name("€100 Poker Chips");
            if(cart.getCartItemsList().size() == 0){
                item.setItemId(1);
            }else{
                item.setItemId(cart.getCartItemsList().size() + 1);
            }
        }else if (price == 200) {
            item.setItem_cost(200);
            item.setQty(1);
            item.setItem_name("€200 Poker Chips");
            if(cart.getCartItemsList().size() == 0){
                item.setItemId(1);
            }else{
                item.setItemId(cart.getCartItemsList().size() + 1);
            }
        } else if (price == 300) {
            item.setItem_cost(300);
            item.setQty(1);
            item.setItem_name("€300 Poker Chips");
            if(cart.getCartItemsList().size() == 0){
                item.setItemId(1);
            }else{
                item.setItemId(cart.getCartItemsList().size() + 1);
            }
        }else if (price == 500) {
            item.setItem_cost(500);
            item.setQty(1);
            item.setItem_name("€500 Poker Chips");
            if(cart.getCartItemsList().size() == 0){
                item.setItemId(1);
            }else{
                item.setItemId(cart.getCartItemsList().size() + 1);
            }
        }else if (price == 1000) {
            item.setItem_cost(1000);
            item.setQty(1);
            item.setItem_name("€1,000 Poker Chips");
            if(cart.getCartItemsList().size() == 0){
                item.setItemId(1);
            }else{
                item.setItemId(cart.getCartItemsList().size() + 1);
            }
        }

        //add item to cart
        cart.getCartItemsList().add(item);

        //loop through cart to get subtotal
        int subtlt = 0;
        for (CartItems itms : cart.getCartItemsList()){
            subtlt = subtlt + (itms.getItem_cost() * itms.getQty());
        }
        cart.setSub_tlt(subtlt);

        //create session variables to display cart details on cart view page
        session.setAttribute("cart", cart);

        return new ModelAndView("viewCart", "cartItem", new CartItems());
    }

    @PostMapping("/updateCart/{id}")
    public String updateCartChipQty(@PathVariable int id, HttpSession session){

        return null;
    }

    public List<Leaderboard> getLeaderboardData(List<GameDetails> gameDetailsData, Authentication authentication)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {

        //Decrypt GameDetails Data First
        //Decryption - AES level decryption
        //Get Security Key info from db
        Optional<SecurityKeyInfo> securityKeyInfo = securityKeyInfoService.findById(1);
        SecretKey key = EncryptionDecryptionConfig.getKeyFromPassword(securityKeyInfo.get().getPassword(),securityKeyInfo.get().getSalt());
        String algorithm = "AES/CBC/PKCS5Padding";

        //Change ivParameterSpec from string to array then to an IvParameterSpec variable
        String[] strArray = securityKeyInfo.get().getIv_parameter_spec().split(",");
        byte[] byteArray = new byte[16];
        for (int i = 0; i < strArray.length; i++) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(byteArray);

        //get the top 10 best wins from the list of game
        List<Leaderboard> leaderboard = new ArrayList<>();
        int leaderboardID = 1;
        for (GameDetails dtls : gameDetailsData){

            int amounotWon = Integer.parseInt(EncryptionDecryptionConfig.decrypt(algorithm, dtls.getTotalWon(), key, ivParameterSpec));
            int amountLost = Integer.parseInt(EncryptionDecryptionConfig.decrypt(algorithm, dtls.getTotalLost(), key, ivParameterSpec));

            System.out.println("amount won: " + amounotWon);
            System.out.println("amount lost: " + amountLost);

            Leaderboard leaderboardStat = new Leaderboard();
            leaderboardStat.setLeaderboardId(leaderboardID);
            leaderboardStat.setDate(dtls.getGameEndTime());
            leaderboardStat.setPlayerId(dtls.getPlayerId());
            leaderboardStat.setGameNumber(dtls.getGameId());
            leaderboardStat.setAmountWon(amounotWon - amountLost);
            leaderboardStat.setPlayerName(authentication.getName());

            leaderboard.add(leaderboardStat);

            leaderboardID++;
        }

        //sort the leader board list - only have top 10
        List<Leaderboard> top10 = new ArrayList<>();

        leaderboard.sort((a, b) -> b.compareTo(a));

        for (int i = 0; i < leaderboard.size(); i++) {
            top10.add(leaderboard.get(i));
        }

        return top10;
    }

}
