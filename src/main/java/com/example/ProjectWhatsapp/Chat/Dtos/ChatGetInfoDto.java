package com.example.ProjectWhatsapp.Chat.Dtos;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.User.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatGetInfoDto {
    private UUID chatId;
    private String chatName;
    private boolean isGroupChat;
    private UUID ownerId;
    private List<Member> members;
    public ChatGetInfoDto(Chat chat){
        this.chatId = chat.getChatId();
        this.chatName = chat.getChatName();
        this.isGroupChat = chat.isGroupChat();
        this.ownerId = chat.getOwnerId();
    }
}