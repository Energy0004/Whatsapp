package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.Message.MessageRepository;
import com.example.ProjectWhatsapp.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjectWhatsappApplication {
//	private final UserRepository userRepository;
//	private final MessageRepository messageRepository;
//	public ProjectWhatsappApplication(UserRepository userRepository, MessageRepository messageRepository) {
//		this.userRepository = userRepository;
//		this.messageRepository = messageRepository;
//	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectWhatsappApplication.class, args);
	}
}
