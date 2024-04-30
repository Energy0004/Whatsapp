package com.example.ProjectWhatsapp.Chat;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService{
    private ChatRepository chatRepository;
    @Override
    public ChatDto addChat(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setChatName(chatDto.getChatName());
        chat.setGroupChat(chatDto.isGroupChat());

        Chat savedChat = chatRepository.save(chat);

        ChatDto savedChatDto = new ChatDto();
        savedChatDto.setChatId(savedChat.getChatId());
        savedChatDto.setChatName(savedChat.getChatName());
        savedChatDto.setGroupChat(savedChat.isGroupChat());
        return savedChatDto;
    }
}