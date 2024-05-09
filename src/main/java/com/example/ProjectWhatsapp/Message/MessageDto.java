package com.example.ProjectWhatsapp.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private UUID messageId;
    private String content;
    private LocalDateTime timeStamp;
    private UUID senderId;
    private UUID chatId;
}