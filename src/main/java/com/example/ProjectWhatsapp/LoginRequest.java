package com.example.ProjectWhatsapp;

public class LoginRequest {

    private String phoneNumber;
    private String password;
//    private String username;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest() {}

//    public LoginRequest(String password, String username) {
//        this.password = password;
//        this.username = username;
//    }
    public LoginRequest(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [email=" + phoneNumber + ", password=" + password + "]";
    }

//    @Override
//    public String toString() {
//        return "LoginRequest{" +
//                "password='" + password + '\'' +
//                ", username='" + username + '\'' +
//                '}';
//    }
}