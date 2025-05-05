package com.example.whatsapp.Repository;

import com.example.whatsapp.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmail(String email);
    @Query("select u from User u where u.full_name Like %:query% or u.email Like %:query%")
    public List<User> searchUser(@Param("query") String query);
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    public User findByID(UUID id);

//    @Query("DELETE FROM User u WHERE u.id = ?1")
//    public void deleteByID(UUID id);
}