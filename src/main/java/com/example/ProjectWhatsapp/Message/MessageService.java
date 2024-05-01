package com.example.ProjectWhatsapp.Message;

public interface MessageService {
    MessageDto addMessage(MessageDto messageDto);
    void deleteMessage(int messageId);
}