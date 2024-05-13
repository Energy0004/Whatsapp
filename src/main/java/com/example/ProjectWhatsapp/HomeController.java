package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatRepository;
import com.example.ProjectWhatsapp.Chat.Dtos.ChatWithLastMessage;
import com.example.ProjectWhatsapp.Message.Message;
import com.example.ProjectWhatsapp.Message.MessageLastMessageDto;
import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.Participant.ParticipantService;
import com.example.ProjectWhatsapp.User.Member;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ParticipantService participantService;
    @GetMapping
    public ResponseEntity<List<ChatWithLastMessage>> findChatByUserIdHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        System.out.println(user + " %%");
        List<Chat> chats = this.chatRepository.findAllChatByUserId(user.getUserId());
        chats.sort(Comparator.comparing(chat -> getLastSentMessage((Chat) chat).getTimeStamp()).reversed());
        List<ChatWithLastMessage> chatWithLastMessages = new ArrayList<>();
        for (Chat chat : chats) {
            ChatWithLastMessage chatWithLastMessage = new ChatWithLastMessage();
            chatWithLastMessage.setChatId(chat.getChatId());
            chatWithLastMessage.setChatName(chat.getChatName());
            chatWithLastMessage.setGroupChat(chat.isGroupChat());
            chatWithLastMessage.setOwnerId(chat.getOwnerId());
            chatWithLastMessage.setMembers(getMembers(chat.getChatId()));
            Message message = getLastSentMessage(chat);
            if(message.getContent() != null) {
                chatWithLastMessage.setLastMessage(new MessageLastMessageDto(message.getContent(), message.getTimeStamp(), message.getType()));
            }
            chatWithLastMessages.add(chatWithLastMessage);
        }
        return new ResponseEntity<>(chatWithLastMessages, HttpStatus.OK);
    }
    private Message getLastSentMessage(Chat chat) {
        List<Message> messages = this.chatRepository.getMessages(chat.getChatId());
        if (messages.isEmpty()) {
            Message message = new Message();
            message.setTimeStamp(LocalDateTime.MIN);
            return message;
        }
        messages.sort(Comparator.comparing(Message::getTimeStamp));

        return messages.get(messages.size() - 1);
    }
    private List<Member> getMembers(UUID chatId) throws Exception {
       List<Member> members = new ArrayList<>();
       List<Participant> participants = participantService.findAllParticipants(chatId);
        for (Participant participant : participants) {
            UUID userId = participant.getUserId();
            Member memberDto = new Member();
            memberDto.setUserId(userId);
            memberDto.setUsername(userService.findUserById(userId).getUsername());
            members.add(memberDto);
        }
        return members;
    }
}