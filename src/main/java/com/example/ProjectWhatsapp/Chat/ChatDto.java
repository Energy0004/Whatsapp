package com.example.ProjectWhatsapp.Chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private int chatId;
    private String chatName;
    private boolean isGroupChat;
}