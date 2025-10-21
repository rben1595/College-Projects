package com.example.fypblackjacksecurity.repos;

import com.example.fypblackjacksecurity.models.SecurityKeyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityKeyInfoRepo extends JpaRepository<SecurityKeyInfo, Integer> {
}
