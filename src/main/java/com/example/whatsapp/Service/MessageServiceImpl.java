package com.example.whatsapp.Service;

import com.example.whatsapp.Exception.ChatException;
import com.example.whatsapp.Exception.MessageException;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.Chat;
import com.example.whatsapp.Modal.Message;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Repository.MessageRepository;
import com.example.whatsapp.Request.SendMessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{
    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    @Override
    public Message sendMessage(SendMessageRequest sendMessageRequest) throws UserException, ChatException {
        User user = userService.findByUserId(sendMessageRequest.getUserId());
        Chat chat = chatService.findChatById(sendMessageRequest.getChatId());
        Message message = new Message();
        message.setUser(user);
        message.setChat(chat);
        message.setContent(sendMessageRequest.getContent());
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
    @Override
    public List<Message> getChatsMessages(UUID chatId, User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);
        if(!chat.getUsers().contains(reqUser)){
            throw new UserException("You are not related to this chat " + chat.getId());
        }
        return messageRepository.findByChatId(chat.getId());
    }
    @Override
    public Message findMessageById(UUID messageId) throws MessageException {
        Message message = messageRepository.findByID(messageId);
        if(message == null){
            throw new MessageException("Message not found with id " + messageId);
        }
        return message;
    }
    @Override
    public void deleteMessage(UUID messageId, User reqUser) throws MessageException, UserException {
        Message message = findMessageById(messageId);
        if(message.getUser().getId().equals(reqUser.getId())){
            messageRepository.deleteById(messageId);
        }
        throw new UserException("You cannot delete another user's message " + reqUser.getFull_name());
    }
}