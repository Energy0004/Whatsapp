package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.User.User;

import java.util.UUID;

public interface ChatService {
    Chat createChat(User user, UUID reqUserId) throws Exception;
    void deleteGroupChat(UUID chatId) throws Exception;
    public void leaveGroupChat(UUID chatId, UUID userId) throws Exception;
    public Chat findChatByChatId(UUID chatId) throws Exception;
    public void addOwnerChat(UUID reqUserId, UUID chatId) throws Exception;
    public Chat createChatForOwner(String chatName, User owner);
    public Chat findChatOwner(UUID chatId,UUID id) throws Exception;
}