package com.example.ProjectWhatsapp.User;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String username;
    private String password;
    private LocalDate lastLogin;
    private String status;

    public User(String username, String password, LocalDate lastLogin, String status) {
        this.username = username;
        this.password = password;
        this.lastLogin = lastLogin;
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(lastLogin, user.lastLogin) && Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, lastLogin, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastLogin=" + lastLogin +
                ", status='" + status + '\'' +
                '}';
    }
}