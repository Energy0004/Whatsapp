package com.example.ProjectWhatsapp.Participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {
    private UUID participantId;
    private UUID userId;
    private UUID chatId;
    private LocalDate joinedAt;
}