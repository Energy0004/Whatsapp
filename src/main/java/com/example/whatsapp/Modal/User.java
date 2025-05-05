package com.example.whatsapp.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String full_name;
    private String email;
    private String profile_picture;
    private String password;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}