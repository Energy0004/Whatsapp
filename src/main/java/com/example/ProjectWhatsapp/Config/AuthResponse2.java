package com.example.ProjectWhatsapp.Config;

import java.util.UUID;

public class AuthResponse2 {

    private String jwt;
    private boolean isAuth;
    private String username;
    private UUID userId;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public AuthResponse2() {}

    public AuthResponse2(String jwt, boolean isAuth, String username, UUID userId) {
        this.jwt = jwt;
        this.isAuth = isAuth;
        this.username = username;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuthResponse2 [" +
                "jwt='" + jwt + '\'' +
                ", isAuth=" + isAuth +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ']';
    }
//
//    @Override
//    public String toString() {
//        return "AuthResponse [jwt=" + jwt + ", isAuth=" + isAuth + "]";
//    }
}