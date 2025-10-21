package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.GameDetails;
import com.example.fypblackjacksecurity.models.SecurityKeyInfo;
import com.example.fypblackjacksecurity.models.User;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.security.logging;
import com.example.fypblackjacksecurity.services.GameDetailsService;
import com.example.fypblackjacksecurity.services.SecurityKeyInfoService;
import com.example.fypblackjacksecurity.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class GameDetailsController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityKeyInfoService securityKeyInfoService;

    @Autowired
    private GameDetailsService gameDetailsService;

    @PostMapping("/updateGameDetails") // Endpoint URL to receive the POST request
    public String handlePostRequest(@RequestBody String param, HttpSession session, Authentication authentication) {

        // Process the received data
        System.out.println("Received data: " + param);

        //update session variable to new account total
        int account_credits = Integer.parseInt(param);
        session.setAttribute("account_credits", account_credits);

        //update account credits
        Optional<User> user = userService.findUserByEmail(encryptEmail(authentication.getName()));
        if(user.isPresent()){
            user.get().setAccount_credits(account_credits);
            System.out.println(user.get().getAccount_credits());
            userService.saveUser(user.get());
        }

        // Optionally, you can return a response
        return "Received POST request successfully";
    }

    @PostMapping("/addToLogFile") // Endpoint URL to receive the POST request
    public String addToLogFile(@RequestBody List<String> filetext, HttpSession session, Authentication authentication)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        logging log = new logging();
        String filePath = (String) session.getAttribute("newLogFile");

        //encrypt log file data
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

        List<String> encryptedText = new ArrayList<>();
        for (String plainText : filetext){
            Date date = new Date();
            encryptedText.add(EncryptionDecryptionConfig.encrypt(algorithm, date + " - " + plainText, key, ivParameterSpec));
        }
        log.WriteToFile(encryptedText, filePath);
        return "text was added to log file";
    }

    @GetMapping("/getLogFileInformation")
    public List<String> getLogFileInformation(HttpSession session){
        String fileName = (String) session.getAttribute("newLogFileName");
        logging log = new logging();
        return log.readLogFile(fileName);
    }

    @GetMapping("/retrieveBettingDetails")
    public Integer retrieveBettingDetails(HttpSession session){
        // Return the response to the client
        return (Integer) session.getAttribute("account_credits");
    }

    @PostMapping("/addGameDetailsToDb")
    public String gameOver(@RequestBody GameDetails gameDetails, HttpSession session, Authentication authentication)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        GameDetails gameData = (GameDetails) session.getAttribute("gameData");
        gameData.setTotalBet(gameDetails.getTotalBet());
        gameData.setTotalLost(gameDetails.getTotalLost());
        gameData.setTotalWon(gameDetails.getTotalWon());
        gameData.setNumberHandsWon(gameDetails.getNumberHandsWon());
        gameData.setGameEndTime(new Date());

        //Decryption - AES level decryption - Decrypt the users password
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

        //create encrypted GameDetails Object
        GameDetails encryptedGameDetailsObject = new GameDetails(0, gameData.getPlayerId(),
                EncryptionDecryptionConfig.encrypt(algorithm, String.valueOf(gameData.getTotalBet()), key, ivParameterSpec),
                EncryptionDecryptionConfig.encrypt(algorithm, String.valueOf(gameData.getTotalWon()), key, ivParameterSpec),
                EncryptionDecryptionConfig.encrypt(algorithm, String.valueOf(gameData.getTotalLost()), key, ivParameterSpec),
                gameData.getNumberHandsWon(),
                gameData.getGameStartTime(),
                gameData.getGameEndTime());

        gameDetailsService.saveGameDetailsToDb(encryptedGameDetailsObject);

        return "game details now saved & encrypted";
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
