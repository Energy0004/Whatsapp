package com.example.ProjectWhatsapp.Participant;

import java.util.List;

public interface ParticipantService {
    ParticipantDto addParticipant(ParticipantDto participantDto);
    void deleteParticipant(int participantId);

    public List<Participant> findAllParticipants(int chatId);
}