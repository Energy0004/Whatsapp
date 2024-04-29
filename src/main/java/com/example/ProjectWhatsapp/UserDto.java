package com.example.ProjectWhatsapp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int userId;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDate lastLogin;
    private String status;
}