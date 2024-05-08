package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.Participant.ParticipantRepository;
import com.example.ProjectWhatsapp.Participant.ParticipantServiceImpl;
import com.example.ProjectWhatsapp.Config.ResourceNotFoundException;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ParticipantServiceImpl participantService;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private UserServiceImpl userService;
    @Override
    public Chat createChat(User user, Integer reqUserId) throws Exception {
        User reqUser = this.userService.findUserById(reqUserId);

        Chat isChatExist = this.chatRepository.findSingleChatByUserIds(user.getUserId(), reqUser.getUserId());

        if (isChatExist != null) {
            return isChatExist;
        }

        Chat chat = new Chat();
        chat.setChatName(reqUser.getUsername());
        chat.setGroupChat(false);
        chat.setOwnerId(-1);
        chat = this.chatRepository.save(chat);

        Participant participant1 = new Participant();
        participant1.setChatId(chat.getChatId());
        participant1.setUserId(user.getUserId());
        participant1.setJoinedAt(LocalDate.now());
        this.participantRepository.save(participant1);

        Participant participant2 = new Participant();
        participant2.setChatId(chat.getChatId());
        participant2.setUserId(reqUser.getUserId());
        participant2.setJoinedAt(LocalDate.now());
        this.participantRepository.save(participant2);


        return chat;
    }
    @Override
    public void addOwnerChat(User reqUser, Integer ownerChatId) throws Exception {
        Participant participant = new Participant();
        participant.setChatId(ownerChatId);
        participant.setUserId(reqUser.getUserId());
        participant.setJoinedAt(LocalDate.now());
        this.participantRepository.save(participant);
    }

    @Override
    public Chat createChatForOwner(User owner) {
        Chat chat = new Chat();
        chat.setChatName("Owner: " + owner.getUsername());
        chat.setGroupChat(true);
        chat.setOwnerId(owner.getUserId());
        this.chatRepository.save(chat);

        Participant participant1 = new Participant();
        participant1.setChatId(chat.getChatId());
        participant1.setUserId(owner.getUserId());
        participant1.setJoinedAt(LocalDate.now());
        this.participantRepository.save(participant1);
        return chat;
    }
    @Override
    public Chat findChatOwner(Integer chatId,Integer id) throws Exception {
        Chat chat = this.chatRepository.findChatOwner(chatId,id);
        if(chat == null){
            throw new Exception("Only owner can delete the chat");
        }
        return chat;
    }
    @Override
    public void deleteGroupChat(int chatId) {
        chatRepository.findById(chatId).orElseThrow(() -> new ResourceNotFoundException("Chat not found with id : " + chatId));
        chatRepository.deleteById(chatId);
    }
    @Override
    public void leaveGroupChat(Integer chatId, Integer userId) throws Exception {
        Chat chat = findChatByChatId(chatId);
        List<Participant> p = participantService.findAllParticipants(chatId);
        if(chat.getOwnerId() == userId && p.size() > 1){
            chat.setOwnerId(p.get(1).getUserId());
            chatRepository.save(chat);
        }
        Participant participant = this.participantRepository.findParticipantByUserId(chat.getChatId(), userId);
        participantRepository.deleteById(participant.getParticipantId());
    }
    @Override
    public Chat findChatByChatId(Integer chatId) throws Exception {
        return this.chatRepository.findById(chatId).orElseThrow(() -> new Exception("The chat is not found"));
    }
}