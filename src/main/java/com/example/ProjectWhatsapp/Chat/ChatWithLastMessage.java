package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Message.MessageLastMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatWithLastMessage {
    private int chatId;
    private String chatName;
    private boolean isGroupChat;
    private int ownerId;
    private MessageLastMessageDto lastMessage;
}