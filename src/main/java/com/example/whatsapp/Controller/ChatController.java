package com.example.whatsapp.Controller;

import com.example.whatsapp.Exception.ChatException;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.Chat;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Request.GroupChatRequest;
import com.example.whatsapp.Request.SingleChatRequest;
import com.example.whatsapp.Service.Response.ApiResponse;
import com.example.whatsapp.Service.ChatService;
import com.example.whatsapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chats")
@AllArgsConstructor
public class ChatController {
    private ChatService chatService;
    private UserService userService;

    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.createGroup(groupChatRequest, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable("chatId") UUID chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        Chat chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatsByUserIdHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable("chatId") UUID chatId, @PathVariable("userId") UUID userId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.addUserToGroup(userId, chatId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @DeleteMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable("chatId") UUID chatId, @PathVariable("userId") UUID userId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        Chat chat = chatService.removeFromGroup(chatId, userId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{chatId}/")
    public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable("chatId") UUID chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        chatService.deleteChat(chatId, reqUser.getId());
        ApiResponse response = new ApiResponse("Chat deleted successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}