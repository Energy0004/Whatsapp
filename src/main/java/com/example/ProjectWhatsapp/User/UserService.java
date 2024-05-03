package com.example.ProjectWhatsapp.User;

import com.example.ProjectWhatsapp.Chat.Chat;

public interface UserService {
    User findUserById(Integer id) throws Exception;
    User findUserByUsername(String username) throws Exception;
    User findUserProfile(String jwt) throws Exception;
    UserDto addUser(UserDto userDto);
    void deleteUser(int userId);
}