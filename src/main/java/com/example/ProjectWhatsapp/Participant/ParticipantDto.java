package com.example.ProjectWhatsapp.Participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {
    private int participantId;
    private int userId;
    private int chatId;
    private LocalDate joinedAt;
}