package com.example.ProjectWhatsapp;

import com.example.ProjectWhatsapp.User.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectWhatsappApplication {
	private final UserRepository userRepository;
	public ProjectWhatsappApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectWhatsappApplication.class, args);
	}
}
