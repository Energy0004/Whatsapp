package com.example.whatsapp.Service;

import com.example.whatsapp.Exception.ChatException;
import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.Chat;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Repository.ChatRepository;
import com.example.whatsapp.Request.GroupChatRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;
    private UserService userService;

    @Override
    public Chat createChat(User reqUser, UUID userId2) throws UserException {
        User user = userService.findByUserId(userId2);
        Chat isChatExist = chatRepository.findSingleChatByUserIds(user, reqUser);
        if(isChatExist != null) {
            return isChatExist;
        }
        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);
        return chatRepository.save(chat);
    }
    @Override
    public Chat findChatById(UUID chatId) throws ChatException {
        Chat chat = chatRepository.findByID(chatId);
        if (chat == null) {
            throw new ChatException("Chat not found with id " + chatId);
        }
        return chat;
    }
    @Override
    public List<Chat> findAllChatByUserId(UUID userId) throws UserException {
        User user = userService.findByUserId(userId);
        return chatRepository.findChatByUserId(user.getId());
    }
    @Override
    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
        Chat group = new Chat();
        group.setGroup(true);
        group.setChat_image(req.getChat_image());
        group.setChat_name(req.getChat_name());
        group.setCreatedBy(reqUser);
        group.getAdmins().add(reqUser);

        for (UUID userId : req.getUserIds()) {
            User user = userService.findByUserId(userId);
            group.getUsers().add(user);
        }
        return chatRepository.save(group);
    }
    @Override
    public Chat addUserToGroup(UUID userId, UUID chatId, User reqUser) throws UserException, ChatException {
        Chat chat = chatRepository.findByID(chatId);
        User user = userService.findByUserId(userId);
        if (chat == null) {
            throw new ChatException("Chat not found with id " + chatId);
        }
        if(chat.getAdmins().contains(reqUser)) {
            chat.getUsers().add(user);
        }else throw new UserException("You are not admin");
        return chatRepository.save(chat);
    }
    @Override
    public Chat renameGroup(UUID chatId, String groupName, User reqUser) throws UserException, ChatException {
        Chat chat = chatRepository.findByID(chatId);
        if (chat == null) {
            throw new ChatException("Chat not found with id " + chatId);
        }
        if(chat.getUsers().contains(reqUser)) {
            chat.setChat_name(groupName);
        }else throw new UserException("You are not member of this group");
        return chatRepository.save(chat);
    }
    @Override
    public Chat removeFromGroup(UUID chatId, UUID userId, User reqUser) throws UserException, ChatException {
        Chat chat = chatRepository.findByID(chatId);
        User user = userService.findByUserId(userId);
        if (chat == null) {
            throw new ChatException("Chat not found with id " + chatId);
        }
        if(chat.getAdmins().contains(reqUser)) {
            chat.getUsers().remove(user);
        } else if (chat.getUsers().contains(reqUser)) {
            if(user.getId().equals(reqUser.getId())){
                chat.getUsers().remove(user);
            }
        } else throw new UserException("You can not remove another user");
        return chatRepository.save(chat);
    }
    @Override
    public void deleteChat(UUID chatId, UUID userId) throws UserException, ChatException {
        Chat chat = findChatById(chatId);
        if(chat != null){
            chatRepository.deleteById(chat.getId());
        }
    }
}