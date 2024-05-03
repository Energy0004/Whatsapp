package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatRepository;
import com.example.ProjectWhatsapp.Chat.ChatServiceImpl;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserRepository;
import com.example.ProjectWhatsapp.User.UserServiceImpl;
import com.example.ProjectWhatsapp.User.signUpController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping
    public ResponseEntity<List<Chat>> findChatByUserIdHandler(@RequestHeader("Authorization") String jwt)
            throws Exception {
        String username  = tokenProvider.getUsernameFromToken(jwt);
        System.out.println(username + " %%");
        User reqUser = this.userRepository.findByUsername(username);
        List<Chat> chats = this.chatRepository.findAllChatByUserId(reqUser.getUserId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
}