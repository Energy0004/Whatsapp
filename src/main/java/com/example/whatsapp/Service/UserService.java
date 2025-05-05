package com.example.whatsapp.Service;

import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Request.UpdateUserRequest;

import java.util.*;

public interface UserService {
    public User findByUserId(UUID id) throws UserException;
    public User findUserProfile(String jwt) throws UserException;
    public User updateUser(UUID userId, UpdateUserRequest req) throws UserException;
    public List<User> searchUser(String query);
}