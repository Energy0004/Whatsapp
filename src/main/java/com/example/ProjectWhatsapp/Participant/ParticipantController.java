package com.example.ProjectWhatsapp.Participant;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/participant")
@AllArgsConstructor
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;
    private ParticipantService participantService;
    @GetMapping
    public List<Participant> getParticipant(){
        return participantRepository.findAll();
//        return List.of(new Participant(1, 2, 3, LocalDate.of(1, Month.JANUARY, 23)));
    }
    @PostMapping
    public ResponseEntity<ParticipantDto> addParticipant(@RequestBody ParticipantDto participantDto){
        ParticipantDto savedParticipant = participantService.addParticipant(participantDto);
        return new ResponseEntity<>(savedParticipant, HttpStatus.CREATED);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable("id") int participantId){
        participantService.deleteParticipant(participantId);
        return ResponseEntity.ok("Participant deleted successfully!.");
// {
//     "userId": 653,
//     "chatId": 2,
//     "joinedAt": "2023-10-11"
// }
    }
}