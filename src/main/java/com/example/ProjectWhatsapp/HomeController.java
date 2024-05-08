package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatRepository;
import com.example.ProjectWhatsapp.Message.Message;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Chat>> findChatByUserIdHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        System.out.println(user + " %%");
        List<Chat> chats = this.chatRepository.findAllChatByUserId(user.getUserId());
        chats.sort(Comparator.comparing(chat -> getLastSentMessage((Chat) chat).getTimeStamp()).reversed());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    private Message getLastSentMessage(Chat chat) {
        List<Message> messages = this.chatRepository.getMessages(chat.getChatId());
        if (messages.isEmpty()) {
            return null;
        }
        messages.sort(Comparator.comparing(Message::getTimeStamp));
        return messages.get(messages.size() - 1);
    }
//    public ResponseEntity<List<Chat>> findChatByUserIdHandler(@RequestHeader("Authorization") String jwt)
//            throws Exception {
//        User user = userService.findUserProfile(jwt);
//        System.out.println(user + " %%");
//        List<Chat> chats = this.chatRepository.findAllChatByUserId(user.getUserId());
//
//        return new ResponseEntity<>(chats, HttpStatus.OK);
//    }
}