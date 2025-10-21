package com.example.fypblackjacksecurity.models;


import com.example.fypblackjacksecurity.repos.LoginDetailsRepo;
import com.example.fypblackjacksecurity.repos.SecurityKeyInfoRepo;
import com.example.fypblackjacksecurity.repos.UserRepo;
import jakarta.servlet.ServletContext;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.session.StandardSession;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Set;

@Service
public class DetailsLogin implements UserDetailsService {
    @Autowired
    LoginDetailsRepo loginRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SecurityKeyInfoRepo securityKeyInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginDetails> user = loginRepo.findByUsername(username);
        if(user == null){
            new UsernameNotFoundException("User not exists by Username");
        }

        //Decryption - AES level decryption - Decrypt the users password
        SecretKey key;
        String algorithm = "AES/CBC/PKCS5Padding";
        String passwordDecrypted;
        //Get Security Key info from db
        Optional<SecurityKeyInfo> securityKeyInfo = securityKeyInfoRepo.findById(1);

        //Change ivParameterSpec from string to array then to an IvParameterSpec variable
        String[] strArray = securityKeyInfo.get().getIv_parameter_spec().split(",");
        byte[] byteArray = new byte[16];
        for (int i = 0; i < strArray.length; i++) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(byteArray);

        try {
            key = EncryptionDecryptionConfig.getKeyFromPassword(securityKeyInfo.get().getPassword(),securityKeyInfo.get().getSalt());
            passwordDecrypted = EncryptionDecryptionConfig.decrypt(algorithm, user.get().getPassword(), key, ivParameterSpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.get().getRole()));

        return new org.springframework.security.core.userdetails.User(username,passwordDecrypted,authorities);
    }
}
