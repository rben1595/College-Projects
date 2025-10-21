package com.example.fypblackjacksecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FypBlackjackSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        //SpringApplication.run(FypBlackjackSecurityApplication.class, args);
        SpringApplication application = new SpringApplication(FypBlackjackSecurityApplication.class);
        application.setAdditionalProfiles("ssl");
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
