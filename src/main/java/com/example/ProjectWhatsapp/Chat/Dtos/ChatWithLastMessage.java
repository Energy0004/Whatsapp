package com.example.ProjectWhatsapp.Chat.Dtos;

import com.example.ProjectWhatsapp.Member;
import com.example.ProjectWhatsapp.Message.MessageLastMessageDto;
import com.example.ProjectWhatsapp.User.UserDto;
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
    private List<Member> members;
}