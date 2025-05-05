package com.example.whatsapp.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String content;
    private LocalDateTime timestamp;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", chat=" + chat +
                '}';
    }
}