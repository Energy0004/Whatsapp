package com.example.ProjectWhatsapp.Chat;

public interface ChatService {
    ChatDto addChat(ChatDto chatDto);
    void deleteChat(int chatId);
}