package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.*;
import com.example.fypblackjacksecurity.repos.LoginDetailsRepo;
import com.example.fypblackjacksecurity.repos.SecurityKeyInfoRepo;
import com.example.fypblackjacksecurity.repos.UserRepo;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.security.logging;
import com.example.fypblackjacksecurity.services.LoginDetailsService;
import com.example.fypblackjacksecurity.services.SecurityKeyInfoService;
import com.example.fypblackjacksecurity.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Controller
public class RegisterController {

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityKeyInfoService securityKeyInfoService;

    @GetMapping("/register")
    public String register(Model model, HttpSession session){
        model.addAttribute("user", new RegisterModel());
        return "register";
    }

    @PostMapping("/register")
    public String formRegister(@ModelAttribute("user") RegisterModel registerModel) {

        //Add login data & user data to db
        //Create a new user & new login details object
        String page;
        try{

            User newUser = new User(0, registerModel.getFirstName(), registerModel.getLastName(), registerModel.getEmail(),
                    registerModel.getPhoneNumber(), registerModel.getAddress(),
                    registerModel.getCountry(), registerModel.getPostCode(), 0);

            //Pass user object to encryptData method then save to db
            Optional<SecurityKeyInfo> securityKeyInfo = encryptUserData(newUser);

            //Save to db
            userService.saveUser(newUser);

            int Id = 0;
            List<User> users = userService.findAll();
            for(User user : users){
                if(user.getEmail().equals(registerModel.getEmail())){
                    Id = user.getUserId();
                }
            }

            String encryptedPassword = encryptPassword(registerModel.getPassword(), securityKeyInfo);

            LoginDetails newLoginDetails = new LoginDetails(Id, registerModel.getUsername(), encryptedPassword, "user");
            loginDetailsService.saveLoginDetails(newLoginDetails);

            page = "login";

        }catch (Exception ex){
            System.out.println("Exception: " + ex);
            page = "error";
        }

        return page;
    }

    public String encryptPassword(String password, Optional<SecurityKeyInfo> securityKeyInfo) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        //Encryption - AES level encryption
        SecretKey key = EncryptionDecryptionConfig.getKeyFromPassword(securityKeyInfo.get().getPassword(),securityKeyInfo.get().getSalt());
        String algorithm = "AES/CBC/PKCS5Padding";
        String[] strArray = securityKeyInfo.get().getIv_parameter_spec().split(",");
        byte[] byteArray = new byte[16];
        for (int i = 0; i < strArray.length; i++) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(byteArray);
        return EncryptionDecryptionConfig.encrypt(algorithm, password, key, ivParameterSpec);
    }

    public Optional<SecurityKeyInfo> encryptUserData(User newUser) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

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

        //Encrypt User Data
        String encryptedEmail = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getEmail(), key, ivParameterSpec);
        String encryptedAddress = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getAddress(), key, ivParameterSpec);
        String encryptedCountry = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getCountry(), key, ivParameterSpec);
        String encryptedFirstName = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getFirstName(), key, ivParameterSpec);
        String encryptedLastName = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getLastName(), key, ivParameterSpec);
        String encryptedPhoneNumber = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getPhoneNumber(), key, ivParameterSpec);
        String encryptedPostCode = EncryptionDecryptionConfig.encrypt(algorithm, newUser.getPostCode(), key, ivParameterSpec);

        //Set user data to encrypted data
        newUser.setEmail(encryptedEmail);
        newUser.setAddress(encryptedAddress);
        newUser.setCountry(encryptedCountry);
        newUser.setFirstName(encryptedFirstName);
        newUser.setLastName(encryptedLastName);
        newUser.setPhoneNumber(encryptedPhoneNumber);
        newUser.setPostCode(encryptedPostCode);

        return securityKeyInfoOptional;
    }
}
