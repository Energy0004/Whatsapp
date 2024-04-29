package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Override
    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setLastLogin(userDto.getLastLogin());
        user.setStatus(userDto.getStatus());

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setUserId(savedUser.getUserId());
        savedUserDto.setUsername(savedUser.getUsername());
        savedUserDto.setPassword(savedUser.getPassword());
        savedUserDto.setPhoneNumber(savedUser.getPhoneNumber());
        savedUserDto.setLastLogin(savedUser.getLastLogin());
        savedUserDto.setStatus(savedUser.getStatus());
        return savedUserDto;
    }
}