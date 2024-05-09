package com.example.ProjectWhatsapp.User;

import java.util.UUID;

public class UserInfo {
    private String username;
    private UUID userId;

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
    public UserInfo(){}
    public UserInfo(String username, UUID userId) {
        this.username = username;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }
}