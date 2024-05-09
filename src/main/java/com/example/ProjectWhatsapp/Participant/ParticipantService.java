package com.example.ProjectWhatsapp.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {
    ParticipantDto addParticipant(ParticipantDto participantDto);
    public void deleteParticipant(UUID participantId) throws Exception;
    public void deleteParticipant(UUID userId, UUID chatId) throws Exception;
    public List<Participant> findAllParticipants(UUID chatId);
    public void addParticipantToGroupByUserId(UUID userId, UUID chatId) throws Exception;
}