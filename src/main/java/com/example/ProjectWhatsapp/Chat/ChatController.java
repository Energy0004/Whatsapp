package com.example.ProjectWhatsapp.Chat;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    private ChatService chatService;
    @GetMapping
    public List<Chat> getChat(){
        return chatRepository.findAll();
//        return List.of(new Chat(1,"name",false));
    }
    @PostMapping
    public ResponseEntity<ChatDto> addChat(@RequestBody ChatDto chatDto){
        ChatDto savedChat = chatService.addChat(chatDto);
        return new ResponseEntity<>(savedChat, HttpStatus.CREATED);
// {
//     "chatName": "Wowo",
//     "isGroupChat": false
// }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteChat(@PathVariable("id") int chatId){
        chatService.deleteChat(chatId);
        return ResponseEntity.ok("Chat deleted successfully!.");
    }
}