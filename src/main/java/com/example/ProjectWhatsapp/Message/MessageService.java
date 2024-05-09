package com.example.ProjectWhatsapp.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageDto addMessage(MessageDto messageDto);
    void deleteMessage(UUID messageId) throws Exception;

    List<Message> getMessagesByChatId(UUID chatId);

    Message sendMessage(UUID chatId, MessageDto messageDto);

    Message findMessageByMessageId(UUID messageId);
    public void participantAndChatContains(UUID chatId, UUID userId) throws Exception;
}