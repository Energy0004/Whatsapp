package com.example.ProjectWhatsapp.User;

import com.example.ProjectWhatsapp.Config.AuthResponse2;
import com.example.ProjectWhatsapp.Config.CustomUserService;
import com.example.ProjectWhatsapp.Config.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserService customUserService;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
//        return List.of(new User(1, "Alish", "ppp", "993933", LocalDate.of(2000, Month.JANUARY, 5), "acitve"));
    }
    @GetMapping("{userId}")
    public ResponseEntity<UserInfo> getUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserById(userId);
        UserInfo userInfo = new UserInfo(user.getUsername(), userId);
        return new ResponseEntity<>(userInfo, HttpStatus.ACCEPTED);
    }
    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.customUserService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        System.out.println(password);
        System.out.println(userDetails.getPassword());
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        System.out.println("Success!!!");

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.addUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
// {
//     "username": "Alish",
//     "password": "pps",
//     "phoneNumber": "993933",
//     "lastLogin": "2020-11-11",
//     "status": "active"
// }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!.");
    }
//    @PostMapping
//    public String createUser(@RequestBody User user) throws Exception {
//        String number = user.getPhoneNumber();
//        User isUser = userRepository.findByPhoneNumber(number);
//        if(isUser != null){
//            throw new Exception("Phone number is used with another account");
//        }
//        User newUser = new User();
//        user.setUsername(user.getUsername());
//        user.setPassword(user.getPassword());
//        user.setPhoneNumber(user.getPhoneNumber());
//        user.setLastLogin(user.getLastLogin());
//        user.setStatus(user.getStatus());
//        userRepository.save(newUser);
//        return "User saved successfully";
//    }
//    @DeleteMapping("{customerId}")
//    public String deleteCustomer(@PathVariable("customerId") Integer id){
//        customerRepository.deleteById(id);
//        return "Customer deleted successfully";
//    }
}