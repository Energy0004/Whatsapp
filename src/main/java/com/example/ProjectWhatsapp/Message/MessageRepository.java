package com.example.ProjectWhatsapp.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m WHERE m.chatId = ?1 ORDER BY timeStamp")
    List<Message> getMessagesByChatId( int chatId);
}