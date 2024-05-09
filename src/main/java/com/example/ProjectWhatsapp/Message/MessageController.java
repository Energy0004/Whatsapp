package com.example.ProjectWhatsapp.Message;

import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
@AllArgsConstructor
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserServiceImpl userService;
    @PostMapping
    public ResponseEntity<MessageDto> addMessage(@RequestBody MessageDto messageDto){
        MessageDto savedMessage = messageService.addMessage(messageDto);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
// {
//     "chatId": 52,
//     "content": "Hello",
//     "senderId": 52,
//     "timeStamp": "2024-04-30"
// }
    }
    @GetMapping("/chat/{chatId}")
    public List<Message> getMessage(@PathVariable("chatId") UUID chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User currentUser = userService.findUserProfile(jwt);
        messageService.participantAndChatContains(chatId, currentUser.getUserId());
        return messageService.getMessagesByChatId(chatId);
    }
    @PostMapping("/chat/{chatId}")
    public ResponseEntity<Message> sendMessage(@PathVariable("chatId") UUID chatId, @RequestBody MessageDto messageDto, @RequestHeader("Authorization") String jwt) throws Exception {
        messageDto.setSenderId(userService.findUserProfile(jwt).getUserId());
        messageDto.setTimeStamp(LocalDateTime.now());
        Message saved = messageService.sendMessage(chatId, messageDto);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<String> cancelSendMessage(@PathVariable("messageId") UUID messageId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        Message message = messageService.findMessageByMessageId(messageId);
        if(user.getUserId() == message.getSenderId()){
            messageService.deleteMessage(message.getMessageId());
        }else throw new Exception("You can not delete someone's message");
        return new ResponseEntity<>("Message deleted successfully!", HttpStatus.OK);
    }
}
