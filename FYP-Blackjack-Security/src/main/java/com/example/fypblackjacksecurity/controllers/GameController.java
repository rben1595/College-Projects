package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.GameDetails;
import com.example.fypblackjacksecurity.models.SecurityKeyInfo;
import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.repos.UserRepo;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.security.logging;
import com.example.fypblackjacksecurity.services.SecurityKeyInfoService;
import com.example.fypblackjacksecurity.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityKeyInfoService securityKeyInfoService;

    @GetMapping("/game")
    public String game(HttpSession session, Authentication authentication) throws IOException {

        Optional<User> loggedInUser;
        String page;

        if(authentication.isAuthenticated()){

            //get user details from the user that has been authenticated
            loggedInUser = userService.findUserByEmail(encryptEmail(authentication.getName()));

            //Set Session Data + Get the betting account total
            session.setAttribute("account_credits", loggedInUser.get().getAccount_credits());
            session.setAttribute("betting_total", 0);

            //Create new Game Session
            GameDetails newGame = new GameDetails();

            //Set Player Id
            newGame.setPlayerId(loggedInUser.get().getUserId());
            //Set Game Start Time
            newGame.setGameStartTime(new Date());
            session.setAttribute("gameData", newGame);

            //create a new log file for the new game thats being requested by the user
            logging log = new logging();
            String filePath = log.CreateFile(session);
            //create session var to keep track of which log file has been created
            session.setAttribute("newLogFile", filePath);
            //write data to log file
            if(filePath.isEmpty()){
                System.out.println("An error has occured when creating the log file.");
            }else{
                List<String> text = new ArrayList<>();
                Date newDate = new Date();
                text.add(newDate + " - " + authentication.getName() + " has connected to the server");
                text.add(newDate + " - " + "initial game information has been created");
                text.add(newDate + " - " + "server is directing user to the game session now");
                log.WriteToFile(text, filePath);
            }
            page = "game";
        }else{
            page = "error";
        }
        return page;
    }

    public String encryptEmail(String email){

        //Decryption - AES level decryption - Decrypt the users password
        SecretKey key;
        String algorithm = "AES/CBC/PKCS5Padding";
        String emailEncrypted;
        //Get Security Key info from db
        Optional<SecurityKeyInfo> securityKeyInfo = securityKeyInfoService.findById(1);

        //Change ivParameterSpec from string to array then to an IvParameterSpec variable
        String[] strArray = securityKeyInfo.get().getIv_parameter_spec().split(",");
        byte[] byteArray = new byte[16];
        for (int i = 0; i < strArray.length; i++) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(byteArray);

        try {
            key = EncryptionDecryptionConfig.getKeyFromPassword(securityKeyInfo.get().getPassword(),securityKeyInfo.get().getSalt());
            emailEncrypted = EncryptionDecryptionConfig.encrypt(algorithm, email, key, ivParameterSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        return emailEncrypted;
    }
}
