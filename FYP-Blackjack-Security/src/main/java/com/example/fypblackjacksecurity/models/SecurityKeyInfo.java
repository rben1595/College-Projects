package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "security_key_info", schema = "FYP-DB", catalog = "")
public class SecurityKeyInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "security_id")
    private int securityId;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "salt")
    private String salt;

    @Basic
    @Column(name = "iv_parameter_spec")
    private String iv_parameter_spec;

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getIv_parameter_spec() {
        return iv_parameter_spec;
    }

    public void setIv_parameter_spec(String iv_parameter_spec) {
        this.iv_parameter_spec = iv_parameter_spec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityKeyInfo that = (SecurityKeyInfo) o;
        return securityId == that.securityId && Objects.equals(password, that.password) && Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityId, password, salt);
    }
}
