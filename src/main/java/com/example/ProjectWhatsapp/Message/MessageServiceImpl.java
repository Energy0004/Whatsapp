package com.example.ProjectWhatsapp.Message;

import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void deleteMessage(int messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message not found with id : " + messageId));
        messageRepository.deleteById(messageId);
    }

    @Override
    public List<Message> getMessagesByChatId(int chatId) {
        return messageRepository.getMessagesByChatId(chatId);
    }

    @Override
    public Message sendMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setChatId(messageDto.getChatId());
        message.setContent(messageDto.getContent());
        message.setTimeStamp(messageDto.getTimeStamp());
        message.setSenderId(messageDto.getSenderId());
        messageRepository.save(message);
        return message;
    }
}