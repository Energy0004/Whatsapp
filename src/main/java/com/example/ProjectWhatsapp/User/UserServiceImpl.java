package com.example.ProjectWhatsapp.User;

import com.example.ProjectWhatsapp.ResourceNotFoundException;
import com.example.ProjectWhatsapp.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Override
    public User findUserByUsername(String username) throws Exception {
        User user = this.userRepository.findByUsername(username);
        if(user == null){
            throw new Exception("Required user does not exist");
        }
        return user;
    }
    @Override
    public User findUserById(Integer id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("The user is not found"));
    }
    @Override
    public User findUserProfile(String jwt) throws Exception {
        String username = this.tokenProvider.getUsernameFromToken(jwt);

        if (username == null) {
            throw new BadCredentialsException("Recieved invalid token... ");
        }

        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new Exception("User not found with the provided username ");
        }
        return user;
    }
    @Override
    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setLastLogin(userDto.getLastLogin());
        user.setStatus(userDto.getStatus());

        User savedUser = userRepository.save(user);

        UserDto savedUserDto = new UserDto();
        savedUserDto.setUserId(savedUser.getUserId());
        savedUserDto.setUsername(savedUser.getUsername());
        savedUserDto.setPassword(savedUser.getPassword());
        savedUserDto.setLastLogin(savedUser.getLastLogin());
        savedUserDto.setStatus(savedUser.getStatus());
        return savedUserDto;
    }
    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
        userRepository.deleteById(userId);
    }
}