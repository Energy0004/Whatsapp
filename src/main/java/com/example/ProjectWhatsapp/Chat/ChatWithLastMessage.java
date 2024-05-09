package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Message.MessageLastMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatWithLastMessage {
    private UUID chatId;
    private String chatName;
    private boolean isGroupChat;
    private UUID ownerId;
    private MessageLastMessageDto lastMessage;
}