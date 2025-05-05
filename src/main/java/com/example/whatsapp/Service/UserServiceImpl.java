package com.example.whatsapp.Service;

import com.example.whatsapp.Config.TokenProvider;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Repository.UserRepository;
import com.example.whatsapp.Request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenProvider tokenProvider;
    @Override
    public User findByUserId(UUID id) throws UserException {
        User user = userRepository.findByID(id);
        if(user == null){
            throw new UserException("User not found with id " + id);
        }
        return user;
    }
    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email = tokenProvider.getEmailFromToken(jwt);
        if(email == null){
            throw new BadCredentialsException("Received invalid token... ");
        }
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("User not found with email " + email);
        }
        return user;
    }
    @Override
    public User updateUser(UUID userId, UpdateUserRequest req) throws UserException {
        User user = findByUserId(userId);
        if(req.getFull_name() != null){
            user.setFull_name(req.getFull_name());
        }
        if(req.getProfile_picture() != null){
            user.setProfile_picture(req.getProfile_picture());
        }
        return userRepository.save(user);
    }
    @Override
    public List<User> searchUser(String query) {
        List<User> users = userRepository.searchUser(query);
        return users;
    }
}