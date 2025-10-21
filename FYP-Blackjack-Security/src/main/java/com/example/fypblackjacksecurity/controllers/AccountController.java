package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.*;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.services.LeaderboardService;
import com.example.fypblackjacksecurity.services.LoginDetailsService;
import com.example.fypblackjacksecurity.services.SecurityKeyInfoService;
import com.example.fypblackjacksecurity.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Autowired
    private SecurityKeyInfoService securityKeyInfoService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/account")
    public String account(HttpSession session, Authentication authentication, Model model) {

        String page;

        try{

            Optional<LoginDetails> userDetails = loginDetailsService.findByUsername(authentication.getName());

            LoginDetails details = new LoginDetails();
            if(userDetails.isPresent()){
                details = userDetails.get();
            }

            session.setAttribute("userRole", details.getRole());

            page = "account";
        }catch(Exception exception){
            System.out.println(exception);
            page = "error";
        }

        return page;
    }

    @PostMapping("/viewAccountDetails")
    public String updateAccountInfo(@ModelAttribute("updateDetails") User user, HttpSession session){



        return null;
    }

    @GetMapping("/viewAccountDetails")
    public String viewInfo(HttpSession session, Authentication authentication, Model model){

        String page;

        try{

            page = "updateAccountInfo";

        }catch(Exception exception){
            page = "error";
        }

        return page;
    }

}
