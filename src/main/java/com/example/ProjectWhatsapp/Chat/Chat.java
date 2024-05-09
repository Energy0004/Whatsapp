package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.rmi.server.UID;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID chatId;
    private String chatName;
    private boolean isGroupChat;
    private UUID ownerId;
    public Chat(String chatName, boolean isGroupChat, UUID ownerId) {
        this.chatName = chatName;
        this.isGroupChat = isGroupChat;
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return chatId == chat.chatId && isGroupChat == chat.isGroupChat && Objects.equals(chatName, chat.chatName) && ownerId == chat.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, chatName, isGroupChat, ownerId);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatName='" + chatName + '\'' +
                ", isGroupChat=" + isGroupChat +
                ", ownerId=" + ownerId +
                '}';
    }
}