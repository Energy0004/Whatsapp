package com.example.ProjectWhatsapp.Participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant,Integer> {
    @Query("select p from Participant p where p.chatId = :chatId and p.userId = :userId")
    public Participant findParticipantByUserId(@Param("chatId") Integer chatId, @Param("userId") Integer userId);
    @Query("select p from Participant p where p.chatId = :chatId")
    public List<Participant> findAllByChatId(@Param("chatId") Integer chatId);
}
