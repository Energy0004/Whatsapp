package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    public Chat findByChatId(UUID chatId);
    @Query("select c from Chat c where c.ownerId = :id AND c.chatId = :chatId")
    public Chat findChatOwner(@Param("chatId") UUID chatId ,@Param("id") UUID id);
    @Query("select c from Chat c Join Participant p On c.chatId = p.chatId JOIN User u ON p.userId = u.userId where c.isGroupChat = false and p.userId = :user and c.isGroupChat = false and p.userId = :reqUser")
    public Chat findSingleChatByUserIds(@Param("user") UUID user, @Param("reqUser") UUID reqUser);
    @Query("SELECT c FROM Chat c JOIN Participant p ON c.chatId = p.chatId JOIN User u ON p.userId = u.userId WHERE u.userId = :query")
    public List<Chat> findAllChatByUserId(@Param("query") UUID userId);
    @Query("select m from Message m where m.chatId = :chatId")
    public List<Message> getMessages(@Param("chatId") UUID chatId);

    @Query("DELETE FROM Chat c WHERE c.chatId = :id")
    public void deleteById(@Param("id") UUID chatId);
}
