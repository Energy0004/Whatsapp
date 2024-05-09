package com.example.ProjectWhatsapp.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    public User findByID(UUID userId);

    @Query("DELETE FROM User u WHERE u.userId = ?1")
    public void deleteUserById(UUID userId);
//    @Query("select u from User u where u.username like %:query%")
//    public List<User> searchUser(@Param("query") String query);
}