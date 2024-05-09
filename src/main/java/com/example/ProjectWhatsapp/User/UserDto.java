package com.example.ProjectWhatsapp.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID userId;
    private String username;
    private String password;
    private LocalDate lastLogin;
    private String status;
}