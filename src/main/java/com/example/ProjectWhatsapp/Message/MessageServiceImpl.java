package com.example.ProjectWhatsapp.Message;

import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.Participant.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ParticipantRepository participantRepository;
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
    public void deleteMessage(UUID messageId) throws Exception {
        Message message = messageRepository.findMessage(messageId);
        if(message == null){
            throw new Exception("Message not found with id :"  + messageId);
        }
        messageRepository.deleteByMessageId(messageId);
    }

    @Override
    public List<Message> getMessagesByChatId(UUID chatId) {
        return messageRepository.getMessagesByChatId(chatId);
    }

    @Override
    public Message sendMessage(UUID chatId, MessageDto messageDto) {
        Message message = new Message();
        message.setChatId(chatId);
        message.setContent(messageDto.getContent());
        message.setTimeStamp(messageDto.getTimeStamp());
        message.setSenderId(messageDto.getSenderId());
        messageRepository.save(message);
        return message;
    }
    @Override
    public Message findMessageByMessageId(UUID messageId) {
        return messageRepository.findMessage(messageId);
    }
    @Override
    public void participantAndChatContains(UUID chatId, UUID userId) throws Exception {
        Participant participant = participantRepository.findParticipantByUserId(chatId, userId);
        if(participant == null){
            throw new Exception("Participant not found");
        }
    }
}