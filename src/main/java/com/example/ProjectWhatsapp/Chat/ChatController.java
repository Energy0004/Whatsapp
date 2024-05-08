package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.Participant.ParticipantServiceImpl;
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
    public ResponseEntity<String> deleteGroupChat(@PathVariable("chatId") Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User owner = userService.findUserProfile(jwt);
        Chat chat = chatService.findChatByChatId(chatId);
        if(owner.getUserId() == chat.getOwnerId()) {
            List<Participant> participants = participantService.findAllParticipants(chat.getChatId());
            for (Participant participant : participants) {
                participantService.deleteParticipant(participant.getParticipantId());
            }
            return ResponseEntity.ok("Chat deleted successfully!");
        }
        throw new Exception("The deletion of the chat is restricted to the owner of the group.");
    }
    @DeleteMapping("/leave/group/{chatId}")
    public ResponseEntity<String> leaveGroupChat(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        chatService.leaveGroupChat(chatId, user.getUserId());
        return ResponseEntity.ok("You left the group");
    }
}
// Zhando
// eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNTE2NjUyOCwiZXhwIjoxNzE1MjUyOTI4LCJ1c2VybmFtZSI6IlpoYW5kbyJ9.VKNpSjbEJP3g-JjBst4u4MqKDM-6BwmAsWd6s5K2l_Z7xl4ktfWC7PF1UBaN993jBZZvFkG1Xm9Fvv-4u-Z0bw
// Arsen
// eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNTE2NjU0MSwiZXhwIjoxNzE1MjUyOTQxLCJ1c2VybmFtZSI6IkFyc2VuIn0.3TCOP7cyYs8uqAenYlZA22InMfPzbselrc_ecX9kYOqBsEXF9nI6Q7w3bDAsarS_kgGspYt61LOa3pHRfzne_g
// Ars
// eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNTE2NjgzMCwiZXhwIjoxNzE1MjUzMjMwLCJ1c2VybmFtZSI6IkFycyJ9.WJf-hTT-zaLCGXCc2azSbU12oEz-0Tf1MTzDq8tM3rXVXrx7OP3U3khoP0PDqfcABXq-B4Qh0KCp60yBc4hV0w