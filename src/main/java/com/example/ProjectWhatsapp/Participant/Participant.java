package com.example.ProjectWhatsapp.Participant;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID participantId;
    private UUID userId;
    private UUID chatId;
    private LocalDate joinedAt;
    public Participant(UUID userId, UUID chatId, LocalDate joinedAt) {
        this.userId = userId;
        this.chatId = chatId;
        this.joinedAt = joinedAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return participantId == that.participantId && userId == that.userId && chatId == that.chatId && Objects.equals(joinedAt, that.joinedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantId, userId, chatId, joinedAt);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", userId=" + userId +
                ", chatId=" + chatId +
                ", joinedAt=" + joinedAt +
                '}';
    }
}