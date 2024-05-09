package com.example.ProjectWhatsapp.User;

import com.example.ProjectWhatsapp.Config.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class signUpController {
//    "http://localhost:8080/api/auth/signup"
//    "http://localhost:8080/api/auth/signin"
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserService customUserService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody UserDto userDto) throws Exception {
        String username = userDto.getUsername();
        User isUser = this.userRepository.findByUsername(username);
        if (isUser != null) {
            throw new Exception("Username is used with another account");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
//        user.setPassword(userDto.getPassword());
        user.setLastLogin(LocalDate.now());
        user.setStatus(userDto.getStatus());
        userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
//        {
//            "username": "Alish",
//            "password": "pps",
//            "phoneNumber": "993933",
//            "lastLogin": "2020-11-11",
//            "status": "active"
//        }
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse2> loginHandler(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        System.out.println(username);
        System.out.println(password);

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.tokenProvider.generateToken(authentication);
        UUID userId = this.userRepository.findByUsername(username).getUserId();

        AuthResponse2 response = new AuthResponse2(jwt, true, username, userId);

        return new ResponseEntity<AuthResponse2>(response, HttpStatus.ACCEPTED);
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
}