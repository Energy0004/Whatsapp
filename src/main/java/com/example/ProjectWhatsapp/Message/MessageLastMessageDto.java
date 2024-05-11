package com.example.ProjectWhatsapp.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageLastMessageDto {
    private String content;
    private LocalDateTime timeStamp;
    private String type;
}