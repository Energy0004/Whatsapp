package com.example.ProjectWhatsapp.Message;

import java.util.List;

public interface MessageService {
    MessageDto addMessage(MessageDto messageDto);
    void deleteMessage(int messageId);

    List<Message> getMessagesByChatId(int chatId);

    Message sendMessage(MessageDto messageDto);
}