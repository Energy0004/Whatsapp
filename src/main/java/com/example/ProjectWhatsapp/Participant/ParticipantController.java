package com.example.ProjectWhatsapp.Participant;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatDto;
import com.example.ProjectWhatsapp.Chat.ChatService;
import com.example.ProjectWhatsapp.Chat.ChatServiceImpl;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserDto;
import com.example.ProjectWhatsapp.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProjectWhatsapp.User.UserDto;

import java.time.LocalDate;
import java.time.Month;
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
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantDto participantDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserById(participantDto.getUserId());
        Participant newParticipant = participantService.addParticipantToGroupByUserId(user.getUserId(), participantDto.getChatId());
        return new ResponseEntity<>(newParticipant, HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteParticipant(@RequestBody ParticipantDto participantDto) throws Exception {
        participantService.deleteParticipant(participantDto.getUserId(), participantDto.getChatId());
        return ResponseEntity.ok("Participant deleted successfully!.");
// {
//     "userId": 653,
//     "chatId": 2,
//     "joinedAt": "2023-10-11"
// }
    }
}