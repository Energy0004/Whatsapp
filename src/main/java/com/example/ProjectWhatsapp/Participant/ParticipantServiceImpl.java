package com.example.ProjectWhatsapp.Participant;

import com.example.ProjectWhatsapp.Config.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public void addParticipantToGroupByUserId(UUID userId, UUID chatId) throws Exception {
        Participant isParticipant = this.participantRepository.findParticipantByUserId(chatId, userId);
        if(isParticipant != null){
            throw new Exception("Participant already exists");
        }
        Participant participant = new Participant();
        participant.setUserId(userId);
        participant.setChatId(chatId);
        participant.setJoinedAt(LocalDate.now());
        participantRepository.save(participant);
//        return participant;
    }
    @Override
    public void deleteParticipant(UUID participantId) throws Exception {
        Participant participant = participantRepository.findParticipant(participantId);
        if(participant == null){
            throw new Exception("Participant not found id :" + participantId);
        }
                participantRepository.deleteParticipant(participantId);
    }
    @Override
    public void deleteParticipant(UUID userId, UUID chatId) throws Exception {
        Participant participant = this.participantRepository.findParticipantByUserId(chatId, userId);
        if(participant == null){
            throw new Exception("Participant does not exist");
        }
        deleteParticipant(participant.getParticipantId());
    }
    @Override
    public List<Participant> findAllParticipants(UUID chatId) {
        return participantRepository.findAllByChatId(chatId);
    }
}