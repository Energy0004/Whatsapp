package com.example.ProjectWhatsapp.User;

public class UserInfo {
    private String username;
    private int userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public UserInfo(){}
    public UserInfo(String username, int userId) {
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