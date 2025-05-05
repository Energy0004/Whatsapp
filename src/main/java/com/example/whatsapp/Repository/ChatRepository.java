package com.example.whatsapp.Repository;

import com.example.whatsapp.Modal.Chat;
import com.example.whatsapp.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    @Query("select c from Chat c join c.users u where u.id = :userId")
    public List<Chat> findChatByUserId(@Param("userId") UUID userId);

    @Query("select c from Chat c where c.isGroup = false and :user member of c.users and :reqUser member of c.users")
    public Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);

    @Query("SELECT c FROM Chat c WHERE c.id = ?1")
    public Chat findByID(UUID id);
}