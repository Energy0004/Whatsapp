package com.example.ProjectWhatsapp.Message;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatDto;
import com.example.ProjectWhatsapp.Chat.ChatServiceImpl;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserDto;
import com.example.ProjectWhatsapp.User.UserService;
import com.example.ProjectWhatsapp.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @Autowired
    private ChatServiceImpl chatService;

    @GetMapping
    public List<Message> getMessages(){
        return messageRepository.findAll();
//        return List.of(new Message(1, "ss", LocalDate.of(2, Month.APRIL, 3), 4, 5));
    }
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
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") int messageId){
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok("Message deleted successfully!.");
    }
    @PostMapping("/chat")
    public List<Message> getMessage(@RequestBody ChatDto chatDto) {
        return messageService.getMessagesByChatId(chatDto.getChatId());
    }
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDto messageDto , @RequestHeader("Authorization") String jwt) throws Exception {
        messageDto.setSenderId(userService.findUserProfile(jwt).getUserId());
        messageDto.setTimeStamp(LocalDate.now());
        Message saved =  messageService.sendMessage(messageDto);
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }
}
