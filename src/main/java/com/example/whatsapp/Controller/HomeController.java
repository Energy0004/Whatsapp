package com.example.whatsapp.Controller;

import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Repository.UserRepository;
import com.example.whatsapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> HomeController(){
        return new ResponseEntity<>("Welcome to our whatsapp api using spring boot", HttpStatus.OK);
    }
}