package com.example.ProjectWhatsapp.Message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;
    private String content;
    private LocalDateTime timeStamp;
    private int senderId;
    private int chatId;

    public Message(String content, LocalDateTime timeStamp, int senderId, int chatId) {
        this.content = content;
        this.timeStamp = timeStamp;
        this.senderId = senderId;
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