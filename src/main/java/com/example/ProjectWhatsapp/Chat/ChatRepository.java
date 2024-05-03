package com.example.ProjectWhatsapp.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    public Chat findByChatId(Integer chatId);
    @Query("select c from Chat c where c.ownerId = :id AND c.chatId = :chatId")
    public Chat findChatOwner(@Param("chatId") Integer chatId ,@Param("id") Integer id);
    @Query("select c from Chat c Join Participant p On c.chatId = p.chatId JOIN User u ON p.userId = u.userId where c.isGroupChat = false and p.userId = :user or p.userId = :reqUser")
    public Chat findSingleChatByUserIds(@Param("user") Integer user, @Param("reqUser") Integer reqUser);
    @Query("SELECT c FROM Chat c JOIN Participant p ON c.chatId = p.chatId JOIN User u ON p.userId = u.userId WHERE u.userId = :query")
    public List<Chat> findAllChatByUserId(@Param("query") Integer userId);
}
