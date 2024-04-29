package com.example.ProjectWhatsapp.Participant;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Participant {
    @Id
    @SequenceGenerator(
            name = "participant_id_sequence",
            sequenceName = "participant_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participant_id_sequence"
    )
    private int participantId;
    private int userId;
    private int chatId;
    private LocalDate joinedAt;

    public Participant(int participantId, int userId, int chatId, LocalDate joinedAt) {
        this.participantId = participantId;
        this.userId = userId;
        this.chatId = chatId;
        this.joinedAt = joinedAt;
    }

    public Participant(int userId, int chatId, LocalDate joinedAt) {
        this.userId = userId;
        this.chatId = chatId;
        this.joinedAt = joinedAt;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
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