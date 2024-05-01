package com.example.ProjectWhatsapp.User;

public interface UserService {
    UserDto addUser(UserDto userDto);
    void deleteUser(int userId);
}