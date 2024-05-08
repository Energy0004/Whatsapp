package com.example.ProjectWhatsapp.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private int messageId;
    private String content;
    private LocalDateTime timeStamp;
    private int senderId;
    private int chatId;
}