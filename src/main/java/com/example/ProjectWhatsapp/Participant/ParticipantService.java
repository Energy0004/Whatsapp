package com.example.ProjectWhatsapp.Participant;

import java.util.List;

public interface ParticipantService {
    ParticipantDto addParticipant(ParticipantDto participantDto);
    public void deleteParticipant(int participantId);
    public void deleteParticipant(int userId, int chatId) throws Exception;
    public List<Participant> findAllParticipants(int chatId);
    public Participant addParticipantToGroupByUserId(int userId, int chatId) throws Exception;
}