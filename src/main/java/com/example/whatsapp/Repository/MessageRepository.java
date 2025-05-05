package com.example.whatsapp.Repository;

import com.example.whatsapp.Modal.Chat;
import com.example.whatsapp.Modal.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("Select m from Message m join m.chat c where c.id = :chatId")
    public List<Message> findByChatId(@Param("chatId") UUID chatId);

    @Query("SELECT m FROM Message m WHERE m.id = ?1")
    public Message findByID(UUID id);
}