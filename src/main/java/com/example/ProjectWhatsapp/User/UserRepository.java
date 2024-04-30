package com.example.ProjectWhatsapp.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
//    public User findByPhoneNumber(String phoneNumber);
//    public User findByUsername(String username);

//    @Query("select u from User u where u.username like %:query% or u.phoneNumber like %:query%")
//    public List<User> searchUser(@Param("query") String query);
}