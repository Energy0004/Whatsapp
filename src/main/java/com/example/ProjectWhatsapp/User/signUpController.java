package com.example.ProjectWhatsapp.User;

import com.example.ProjectWhatsapp.AuthResponse;
import com.example.ProjectWhatsapp.CustomUserService;
import com.example.ProjectWhatsapp.LoginRequest;
import com.example.ProjectWhatsapp.TokenProvider;
import jdk.jshell.spi.ExecutionControl;
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

import java.util.List;

@RestController
@RequestMapping("/auth")
public class signUpController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    private String temp;
    @Autowired
    private CustomUserService customUserService;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
//        return List.of(new User(1, "Alish", "ppp", "993933", LocalDate.of(2000, Month.JANUARY, 5), "acitve"));
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody UserDto userDto) throws Exception {
        String phoneNumber = userDto.getPhoneNumber();
//        List<User> list = this.userRepository.searchUse(phoneNumber);
        User isUser = this.userRepository.findByPhoneNumber(phoneNumber);
        if (isUser != null) {
            throw new Exception("PhoneNumber is used with another account");
        }
        temp = phoneNumber;
//        {
//            "username": "Alish",
//            "password": "pps",
//            "phoneNumber": "993933",
//            "lastLogin": "2020-11-11",
//            "status": "active"
//        }
    }
    @PostMapping("/signup2")
    public ResponseEntity<AuthResponse> signUp2(@RequestBody UserDto userDto) throws Exception {
        User user = new User();
        user.setUsername(userDto.getUsername());
        // createdUser.setPassword(this.passwordEncoder.encode(password));
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
//        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(temp);
        user.setLastLogin(userDto.getLastLogin());
        user.setStatus(userDto.getStatus());
        userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(temp, userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(jwt, true);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
//        UserDto savedUserDto = new UserDto();
//        savedUserDto.setUserId(savedUser.getUserId());
//        savedUserDto.setUsername(savedUser.getUsername());
//        savedUserDto.setPassword(savedUser.getPassword());
//        savedUserDto.setPhoneNumber(savedUser.getPhoneNumber());
//        savedUserDto.setLastLogin(savedUser.getLastLogin());
//        savedUserDto.setStatus(savedUser.getStatus());

//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest request) {

        String phoneNumber = request.getPhoneNumber();
        String password = request.getPassword();
        System.out.println(phoneNumber);
        System.out.println(password);

        Authentication authentication = this.authenticate(phoneNumber, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.tokenProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(jwt, true);

        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);

    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.customUserService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        System.out.println(password);
        System.out.println(userDetails.getPassword());
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(password);
//        System.out.println(password);
//        System.out.println(encodedPassword);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        System.out.println("Success!!!");

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

