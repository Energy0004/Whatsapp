package com.example.whatsapp.Service;

import com.example.whatsapp.Exception.ChatException;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.Chat;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Request.GroupChatRequest;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    public Chat createChat(User reqUser, UUID userId2) throws UserException;
    public Chat findChatById(UUID chatId) throws ChatException;
    public List<Chat> findAllChatByUserId(UUID userId) throws UserException;
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;
    public Chat addUserToGroup(UUID userId, UUID chatId, User reqUser) throws UserException, ChatException;
    public Chat renameGroup(UUID chatId, String groupName, User reqUser) throws UserException, ChatException;
    public Chat removeFromGroup(UUID chatId, UUID userId, User reqUser) throws UserException, ChatException;
    public void deleteChat(UUID chatId, UUID userId) throws UserException, ChatException;
}