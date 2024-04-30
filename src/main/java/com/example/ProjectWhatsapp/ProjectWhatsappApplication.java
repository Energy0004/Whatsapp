package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.Message.MessageRepository;
import com.example.ProjectWhatsapp.User.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
