package com.example.ProjectWhatsapp.Message;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_id_sequence",
            sequenceName = "message_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_id_sequence"
    )
    private int messageId;
    private String content;
    private LocalDate timeStamp;
    private int senderId;
    private int chatId;

    public Message(int messageId, String content, LocalDate timeStamp, int senderId, int chatId) {
        this.messageId = messageId;
        this.content = content;
        this.timeStamp = timeStamp;
        this.senderId = senderId;
        this.chatId = chatId;
    }

    public Message(String content, LocalDate timeStamp, int senderId, int chatId) {
        this.content = content;
        this.timeStamp = timeStamp;
        this.senderId = senderId;
        this.chatId = chatId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId && senderId == message.senderId && chatId == message.chatId && Objects.equals(content, message.content) && Objects.equals(timeStamp, message.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, content, timeStamp, senderId, chatId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                ", senderId=" + senderId +
                ", chatId=" + chatId +
                '}';
    }
}