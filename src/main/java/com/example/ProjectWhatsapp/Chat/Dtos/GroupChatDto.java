package com.example.ProjectWhatsapp.Chat.Dtos;

import com.example.ProjectWhatsapp.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatDto {
    private String chatName;
    private List<UserDto> members;
}