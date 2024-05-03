package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.User.User;

public interface ChatService {
    Chat createChat(User user, Integer reqUserId) throws Exception;

    void deleteGroupChat(int chatId);
    public void leaveGroupChat(Integer chatId, Integer userId) throws Exception;
    public Chat findChatByChatId(Integer chatId) throws Exception;
    public void addOwnerChat(User toAdduser,Integer chatId) throws Exception;

    public Chat createChatForOwner(User owner);
    public Chat findChatOwner(Integer chatId,Integer id) throws Exception;
}