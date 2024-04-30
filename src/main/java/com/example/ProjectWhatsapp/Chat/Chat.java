package com.example.ProjectWhatsapp.Chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int chatId;
    private String chatName;
    private boolean isGroupChat;
    public Chat(String chatName, boolean isGroupChat) {
        this.chatName = chatName;
        this.isGroupChat = isGroupChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return chatId == chat.chatId && isGroupChat == chat.isGroupChat && Objects.equals(chatName, chat.chatName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, chatName, isGroupChat);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", chatName='" + chatName + '\'' +
                ", isGroupChat=" + isGroupChat +
                '}';
    }
}