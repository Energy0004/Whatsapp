package com.example.ProjectWhatsapp.Chat;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Chat {
    @Id
    @SequenceGenerator(
            name = "chat_id_sequence",
            sequenceName = "chat_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chat_id_sequence"
    )
    private int chatId;
    private String chatName;
    private boolean isGroupChat;
    public Chat(int chatId, String chatName, boolean isGroupChat) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.isGroupChat = isGroupChat;
    }
    public Chat(String chatName, boolean isGroupChat) {
        this.chatName = chatName;
        this.isGroupChat = isGroupChat;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setGroupChat(boolean groupChat) {
        isGroupChat = groupChat;
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