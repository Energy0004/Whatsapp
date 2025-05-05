package com.example.whatsapp.Controller;

import com.example.whatsapp.Exception.ChatException;
import com.example.whatsapp.Exception.MessageException;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.Message;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Request.SendMessageRequest;
import com.example.whatsapp.Service.Response.ApiResponse;
import com.example.whatsapp.Service.MessageService;
import com.example.whatsapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest sendMessageRequest, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserProfile(jwt);
        sendMessageRequest.setUserId(user.getId());
        Message message = messageService.sendMessage(sendMessageRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatsMessagesHandler(@PathVariable UUID chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserProfile(jwt);
        List<Message> messages = messageService.getChatsMessages(chatId, user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable UUID messageId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException, MessageException {
        User user = userService.findUserProfile(jwt);
        messageService.deleteMessage(messageId, user);
        ApiResponse response = new ApiResponse("Message deleted successfully", false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}