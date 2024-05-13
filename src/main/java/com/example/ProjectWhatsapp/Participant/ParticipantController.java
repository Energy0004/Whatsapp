package com.example.ProjectWhatsapp.Participant;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatService;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participant")
@AllArgsConstructor
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @GetMapping
    public List<Participant> getParticipant(){
        return participantRepository.findAll();
//        return List.of(new Participant(1, 2, 3, LocalDate.of(1, Month.JANUARY, 23)));
    }
    @PostMapping("/")
    public ResponseEntity<String> addParticipant(@RequestBody ParticipantDto participantDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User owner = userService.findUserProfile(jwt);
        Chat chat = chatService.findChatByChatId(participantDto.getChatId());
        User user = userService.findUserById(participantDto.getUserId());
        if(owner.getUserId().equals(chat.getOwnerId())){
            participantService.addParticipantToGroupByUserId(user.getUserId(), chat.getChatId());
            return ResponseEntity.ok("Participant added");
        }
        throw new Exception("Adding a new participant is limited to the owner of the group.");
    }
    @PostMapping("/ByChatId")
    public ResponseEntity<List<Participant>> find(@RequestBody ParticipantDto participantDto, @RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(participantRepository.findAllByChatId(participantDto.getChatId()),HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteParticipant(@RequestBody ParticipantDto participantDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User owner = userService.findUserProfile(jwt);
        Chat chat = chatService.findChatByChatId(participantDto.getChatId());
        User user = userService.findUserById(participantDto.getUserId());
        if(owner.getUserId().equals(chat.getOwnerId())){
            participantService.deleteParticipant(user.getUserId(), chat.getChatId());
            return ResponseEntity.ok("Participant removed");
        }
        throw new Exception("Removing a participant is limited to the owner of the group.");
// {
//     "userId": 653,
//     "chatId": 2,
//     "joinedAt": "2023-10-11"
// }
    }
}