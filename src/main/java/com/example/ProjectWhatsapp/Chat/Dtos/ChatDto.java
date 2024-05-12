package com.example.ProjectWhatsapp.Chat.Dtos;

import com.example.ProjectWhatsapp.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private int chatId;
    private String chatName;
    private boolean isGroupChat;
}