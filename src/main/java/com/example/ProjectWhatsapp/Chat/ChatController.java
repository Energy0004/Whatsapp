package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.Participant.ParticipantServiceImpl;
import com.example.ProjectWhatsapp.Config.TokenProvider;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserDto;
import com.example.ProjectWhatsapp.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private ParticipantServiceImpl participantService;
    @GetMapping
    public List<Chat> getChat(){
        return chatRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Chat> createChat(@RequestBody UserDto userDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        User reqUser = userService.findUserByUsername(userDto.getUsername());
        Chat savedChat = chatService.createChat(user, reqUser.getUserId());
        return new ResponseEntity<>(savedChat, HttpStatus.CREATED);
// {
//     "chatName": "Wowo",
//     "isGroupChat": false
// }
    }
    @PostMapping("/create/group")
    public ResponseEntity<Chat> createGroupChat(@RequestBody List<UserDto> userDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User owner = userService.findUserProfile(jwt);
        Chat ownerChat = chatService.createChatForOwner(owner);
        for (UserDto dto : userDto) {
            User reqUser = userService.findUserByUsername(dto.getUsername());
            chatService.addOwnerChat(reqUser, ownerChat.getChatId());
        }
        return new ResponseEntity<>(ownerChat, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/group/{chatId}")
    public ResponseEntity<String> deleteGroupChat(@PathVariable("chatId") Integer chatId){
        List<Participant> participants = participantService.findAllParticipants(chatId);
        for (Participant participant : participants) {
            participantService.deleteParticipant(participant.getParticipantId());
        }
        return ResponseEntity.ok("Chat deleted successfully!");
    }
    @DeleteMapping("/leave/group/{chatId}")
    public ResponseEntity<String> leaveGroupChat(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        chatService.leaveGroupChat(chatId, user.getUserId());
        return ResponseEntity.ok("You left the group");
    }
}

//Zhando eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNDc1NDI1OCwiZXhwIjoxNzE0ODQwNjU4LCJ1c2VybmFtZSI6IlpoYW5kbyJ9.qjAYRwY31YoTW1sHyROz5XTlXZBXjDnMj2G41a4E33DYm93nx9PSsl5EouY7xa0_RJR0SZSgm3ZdZ8RbWk6Aww
//Arsen eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNDc0MTI3OCwiZXhwIjoxNzE0ODI3Njc4LCJ1c2VybmFtZSI6IkFyc2VuIn0.cp5m6RbPVzJcTTNtuMPXXVz099e7lnuTWANtw7cGq4bBWiEkkmQKVMPKfAe2MeI2EAeyc24TBEvHifLfbxri5g
//Ars eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNDc1NDYwNywiZXhwIjoxNzE0ODQxMDA3LCJ1c2VybmFtZSI6IkFycyJ9.DOhhZyRozZQzNGmm0vZUVIP6swNuqkjIO_QCmr5vOmpti-HBPysnqYU363Pdw_RFoNA-IT9srAneV6yEYsdEDg