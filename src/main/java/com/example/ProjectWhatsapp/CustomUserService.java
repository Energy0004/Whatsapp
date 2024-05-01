package com.example.ProjectWhatsapp;

import java.util.ArrayList;
import java.util.List;

import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByPhoneNumber(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with provided phone number" + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber() , user.getPassword(), authorities);
    }
}
