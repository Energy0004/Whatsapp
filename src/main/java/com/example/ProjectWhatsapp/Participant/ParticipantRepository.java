package com.example.ProjectWhatsapp.Participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant,Integer> {
    @Query("select p from Participant p where p.chatId = :chatId and p.userId = :userId")
    public Participant findParticipantByUserId(@Param("chatId") UUID chatId, @Param("userId") UUID userId);
    @Query("select p from Participant p where p.chatId = :chatId")
    public List<Participant> findAllByChatId(@Param("chatId") UUID chatId);
    @Query("DELETE FROM Participant c WHERE c.participantId = :participantId")
    public void deleteParticipant(@Param("participantId") UUID participantId);
    @Query("select p from Participant p where p.participantId = ?1")
    public Participant findParticipant(UUID participantId);
}
