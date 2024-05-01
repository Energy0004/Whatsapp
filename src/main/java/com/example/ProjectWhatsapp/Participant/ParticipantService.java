package com.example.ProjectWhatsapp.Participant;

public interface ParticipantService {
    ParticipantDto addParticipant(ParticipantDto participantDto);
    void deleteParticipant(int participantId);
}