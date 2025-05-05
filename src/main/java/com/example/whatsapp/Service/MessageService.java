package com.example.whatsapp.Service;

import com.example.whatsapp.Exception.ChatException;
import com.example.whatsapp.Exception.MessageException;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.Message;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Request.SendMessageRequest;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    public Message sendMessage(SendMessageRequest sendMessageRequest) throws UserException, ChatException;
    public List<Message> getChatsMessages(UUID chatId, User reqUser) throws ChatException, UserException;
    public Message findMessageById(UUID messageId) throws MessageException;
    public void deleteMessage(UUID messageId, User reqUser) throws MessageException, UserException;
}