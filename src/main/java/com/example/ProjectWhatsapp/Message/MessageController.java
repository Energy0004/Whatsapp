package com.example.ProjectWhatsapp.Message;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    private MessageService messageService;
    @GetMapping
    public List<Message> getMessages(){
        return messageRepository.findAll();
//        return List.of(new Message(1, "ss", LocalDate.of(2, Month.APRIL, 3), 4, 5));
    }
    @PostMapping
    public ResponseEntity<MessageDto> addMessage(@RequestBody MessageDto messageDto){
        MessageDto savedMessage = messageService.addMessage(messageDto);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
//        {
//            "chatId": 52,
//            "content": "Hello",
//            "senderId": 52,
//            "timeStamp": "2024-04-30"
//        }
    }
}
