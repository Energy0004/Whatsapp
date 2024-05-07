package com.example.ProjectWhatsapp.Message;

import java.util.List;

public interface MessageService {
    MessageDto addMessage(MessageDto messageDto);
    void deleteMessage(int messageId);

    List<Message> getMessagesByChatId(int chatId);

    Message sendMessage(Integer chatId, MessageDto messageDto);

    Message findMessageByMessageId(int messageId);
    public void participantAndChatContains(Integer chatId, Integer userId) throws Exception;
}