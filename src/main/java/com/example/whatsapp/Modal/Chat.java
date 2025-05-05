package com.example.whatsapp.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String chat_name;
    private String chat_image;
    @ManyToMany
    private Set<User> admins = new HashSet<>();
    @Column(name = "is_group")
    private boolean isGroup;
    @JoinColumn(name = "created_by")
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private Set<User> users = new HashSet<>();
    @OneToMany
    private List<Message> messages = new ArrayList<>();

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", chat_name='" + chat_name + '\'' +
                ", chat_image='" + chat_image + '\'' +
                ", isGroup=" + isGroup +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }
}