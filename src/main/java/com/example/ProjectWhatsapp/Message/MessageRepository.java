package com.example.ProjectWhatsapp.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m WHERE m.chatId = ?1 ORDER BY timeStamp")
    List<Message> getMessagesByChatId(UUID chatId);
    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :id")
    void deleteByMessageId(@Param("id") UUID messageId);
    @Query("SELECT m FROM Message m WHERE m.messageId = :messageId")
    Message findMessage(@Param("messageId") UUID messageId);
}