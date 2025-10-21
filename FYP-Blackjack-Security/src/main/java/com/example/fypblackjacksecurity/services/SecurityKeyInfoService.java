package com.example.fypblackjacksecurity.services;

import com.example.fypblackjacksecurity.models.SecurityKeyInfo;
import com.example.fypblackjacksecurity.repos.SecurityKeyInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityKeyInfoService {
    @Autowired
    private SecurityKeyInfoRepo securityKeyInfoRepo;

    public SecurityKeyInfoService(SecurityKeyInfoRepo securityKeyInfoRepo) {
        this.securityKeyInfoRepo = securityKeyInfoRepo;
    }

    public Optional<SecurityKeyInfo> findById(int Id){
        return securityKeyInfoRepo.findById(Id);
    }
}
