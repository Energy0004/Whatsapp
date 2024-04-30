package com.example.ProjectWhatsapp.Message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{
    private MessageRepository messageRepository;
    @Override
    public MessageDto addMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setTimeStamp(messageDto.getTimeStamp());
        message.setSenderId(messageDto.getSenderId());
        message.setChatId(messageDto.getChatId());

        Message savedMessage = messageRepository.save(message);

        MessageDto savedMessageDto = new MessageDto();
        savedMessageDto.setMessageId(savedMessage.getMessageId());
        savedMessageDto.setContent(savedMessage.getContent());
        savedMessageDto.setTimeStamp(savedMessage.getTimeStamp());
        savedMessageDto.setSenderId(savedMessage.getSenderId());
        savedMessageDto.setChatId(savedMessage.getChatId());
        return savedMessageDto;
    }
}