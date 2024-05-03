package com.example.ProjectWhatsapp.Participant;

import com.example.ProjectWhatsapp.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{
    @Autowired
    private ParticipantRepository participantRepository;
    @Override
    public ParticipantDto addParticipant(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setUserId(participantDto.getUserId());
        participant.setChatId(participantDto.getChatId());
        participant.setJoinedAt(participantDto.getJoinedAt());

        Participant savedParticipant = participantRepository.save(participant);

        ParticipantDto savedParticipantDto = new ParticipantDto();
        savedParticipantDto.setParticipantId(savedParticipant.getParticipantId());
        savedParticipantDto.setUserId(savedParticipant.getUserId());
        savedParticipantDto.setChatId(savedParticipant.getChatId());
        savedParticipantDto.setJoinedAt(savedParticipant.getJoinedAt());
        return savedParticipantDto;
    }

    @Override
    public void deleteParticipant(int participantId) {
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new ResourceNotFoundException("Participant not found with id : " + participantId));
        participantRepository.deleteById(participantId);
    }

    @Override
    public List<Participant> findAllParticipants(int chatId) {
        return participantRepository.findAllByChatId(chatId);
    }
}
