package com.example.fypblackjacksecurity.security;

import com.example.fypblackjacksecurity.models.SecurityKeyInfo;
import com.example.fypblackjacksecurity.models.Token;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Optional;

public class PaymentToken {

    private String iv_param_spec = "110,5,-21,70,-42,14,58,-94,18,96,2,-103,21,2,53,-25,";
    private String encrypted_signature = " ";

    public Token generatePaymentToken(String signature) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {

        Token newToken = new Token();
        newToken.setTokenId(1);
        newToken.setDateIssued(new Date());
        newToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));
        newToken.setSubject("payment-poker-chips");

        //Encryption - AES level encryption
        String algorithm = "AES/CBC/PKCS5Padding";
        String[] strArray = iv_param_spec.split(",");
        byte[] byteArray = new byte[16];
        for (int i = 0; i < strArray.length; i++) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(byteArray);
        encrypted_signature = EncryptionDecryptionConfig.encrypt(algorithm, signature,
                EncryptionDecryptionConfig.getKeyFromPassword(newToken.getSubject(), signature), ivParameterSpec);

        newToken.setSignature(encrypted_signature);

        return newToken;
    }

    public Boolean isTokenExpired(Token paymentToken){
        return paymentToken.getExpiryDate().after(new Date());
    }

    public Boolean validateToken(Token paymentToken){
        if(paymentToken.getSubject().equals("payment-poker-chips")
                && paymentToken.getExpiryDate().after(new Date())){
            return true;
        }else{
            return false;
        }
    }
}
