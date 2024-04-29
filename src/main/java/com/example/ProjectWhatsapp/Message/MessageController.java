package com.example.ProjectWhatsapp.Message;

import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @GetMapping
    public List<Message> getMessages(){
//        return messageRepository.findAll();
        return List.of(new Message(1, "ss", LocalDate.of(2, Month.APRIL, 3), 4, 5));
    }
}
