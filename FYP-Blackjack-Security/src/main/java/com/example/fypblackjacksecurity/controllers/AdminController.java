package com.example.fypblackjacksecurity.controllers;

import com.example.fypblackjacksecurity.models.LoginDetails;
import com.example.fypblackjacksecurity.models.SecurityKeyInfo;
import com.example.fypblackjacksecurity.security.EncryptionDecryptionConfig;
import com.example.fypblackjacksecurity.security.logging;
import com.example.fypblackjacksecurity.services.LoginDetailsService;
import com.example.fypblackjacksecurity.services.SecurityKeyInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private LoginDetailsService loginDetailsService;

    @Autowired
    private SecurityKeyInfoService securityKeyInfoService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/viewLogs")
    public String viewLogs(HttpSession session, Authentication authentication, Model model){

        logging log = new logging();

        String page = "";
        Optional<LoginDetails> accountDetails = loginDetailsService.findByUsername(authentication.getName());
        if(accountDetails.isPresent()){
            try{
                if(accountDetails.get().getRole().equals("admin")){
                    List<File> logFilesList = log.getAllLogFiles();
                    if(logFilesList.isEmpty()){
                        model.addAttribute("listEmptyMsg", "There are no log files on record as of yet.");
                    }else{
                        List<String> fileNames = new ArrayList<>();
                        for (File file : logFilesList){
                            System.out.println(file.getName());
                            fileNames.add(file.getName());
                        }
                        session.setAttribute("fileNames", fileNames);
                        page = "logsView";
                    }
                }else if(accountDetails.get().getRole().equals("user")){
                    page = "error";
                }
            }catch(Exception ex){
                System.out.println("Exception: " + ex);
                page = "error";
            }
        }else{
            page = "error";
        }
        return page;
    }

    @GetMapping("/logFile/{fileName}")
    public String getLogFile(@PathVariable("fileName") String fileName, HttpSession session,
                             Authentication authentication, Model model){

        logging log = new logging();

        String page = "";
        Optional<LoginDetails> accountDetails = loginDetailsService.findByUsername(authentication.getName());
        if(accountDetails.isPresent()){
            try{
                if(accountDetails.get().getRole().equals("admin")){
                    List<String> logFileData = log.readLogFile(fileName);
                    if(logFileData.isEmpty()){
                        model.addAttribute("listEmptyMsg", "This log file has no data");
                    }else{
                        //decrypt log file data
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
                        //create plaintext list
                        List<String> plaintextData = new ArrayList<>();
                        for (String ciphertext : logFileData){
                            plaintextData.add(EncryptionDecryptionConfig.decrypt(algorithm, ciphertext, key, ivParameterSpec));
                        }
                        session.setAttribute("fileData", plaintextData);
                        session.setAttribute("fileName", fileName);
                        page = "logFileData";
                    }
                }else if(accountDetails.get().getRole().equals("user")){
                    page = "error";
                }
            }catch(Exception ex){
                System.out.println("Exception: " + ex);
                page = "error";
            }
        }else{
            page = "error";
        }
        return page;
    }

}
