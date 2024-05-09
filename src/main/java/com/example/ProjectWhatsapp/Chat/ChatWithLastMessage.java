package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Config.Message.MessageLastMessageDto;
import com.example.ProjectWhatsapp.Participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private List<Participant> participants;
}