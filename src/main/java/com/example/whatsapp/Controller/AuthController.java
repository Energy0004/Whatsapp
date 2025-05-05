package com.example.whatsapp.Controller;

import com.example.whatsapp.Config.TokenProvider;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Repository.UserRepository;
import com.example.whatsapp.Request.LoginRequest;
import com.example.whatsapp.Service.Response.AuthResponse;
import com.example.whatsapp.Service.CustomUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;
    private CustomUserService customUserService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String full_name = user.getFull_name();
        String password = user.getPassword();

        User isUser = userRepository.findByEmail(email);

        if(isUser != null){
            throw new UserException("Email is used with another account " + email);
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFull_name(full_name);
        createdUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req){
        String email = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException("Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password or username");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}