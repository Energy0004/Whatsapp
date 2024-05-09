package com.example.ProjectWhatsapp.User;

import com.example.ProjectWhatsapp.Chat.Chat;

import java.util.UUID;

public interface UserService {
    User findUserById(UUID id) throws Exception;
    User findUserByUsername(String username) throws Exception;
    User findUserProfile(String jwt) throws Exception;
    UserDto addUser(UserDto userDto);
    void deleteUser(UUID userId);
}