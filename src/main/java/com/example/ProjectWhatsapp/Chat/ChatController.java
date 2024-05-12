package com.example.ProjectWhatsapp.Chat;

import com.example.ProjectWhatsapp.Chat.Dtos.ChatGetInfoDto;
import com.example.ProjectWhatsapp.Chat.Dtos.GroupChatDto;
import com.example.ProjectWhatsapp.Member;
import com.example.ProjectWhatsapp.Participant.Participant;
import com.example.ProjectWhatsapp.Participant.ParticipantServiceImpl;
import com.example.ProjectWhatsapp.User.User;
import com.example.ProjectWhatsapp.User.UserDto;
import com.example.ProjectWhatsapp.User.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ParticipantServiceImpl participantService;
    @GetMapping("{chatId}")
    public ResponseEntity<ChatGetInfoDto> getChatInfo(@PathVariable UUID chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        Chat chat = chatService.findChatByChatId(chatId);
        List<Participant> participants = participantService.findAllParticipants(chat.getChatId());
        List<Member> members = new ArrayList<>();
        for (Participant participant : participants) {
            User user = userService.findUserById(participant.getUserId());
            Member member = new Member();
            member.setUserId(user.getUserId());
            member.setUsername(user.getUsername());
            members.add(member);
        }
        ChatGetInfoDto chatGetInfoDto = new ChatGetInfoDto(chat);
        chatGetInfoDto.setMembers(members);
        return new ResponseEntity<>(chatGetInfoDto, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Chat> createChat(@RequestBody UserDto userDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        User reqUser = userService.findUserByUsername(userDto.getUsername());
        Chat savedChat = chatService.createChat(user, reqUser.getUserId());
        return new ResponseEntity<>(savedChat, HttpStatus.CREATED);
// {
//     "chatName": "Wowo",
//     "isGroupChat": false
// }
    }
    @PostMapping("/create/group")
    public ResponseEntity<Chat> createGroupChat(@RequestBody GroupChatDto chatDto, @RequestHeader("Authorization") String jwt) throws Exception {
        User owner = userService.findUserProfile(jwt);
        Chat ownerChat = chatService.createChatForOwner(chatDto.getChatName(), owner);
        for (UserDto dto : chatDto.getMembers()) {
            try {
                User reqUser = userService.findUserById(dto.getUserId());
                chatService.addOwnerChat(reqUser.getUserId(), ownerChat.getChatId());
            }catch (Exception e){}
        }
        return new ResponseEntity<>(ownerChat, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/group/{chatId}")
    public ResponseEntity<String> deleteGroupChat(@PathVariable("chatId") UUID chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User owner = userService.findUserProfile(jwt);
        Chat chat = chatService.findChatByChatId(chatId);
        if(owner.getUserId().equals(chat.getOwnerId())) {
            List<Participant> participants = participantService.findAllParticipants(chat.getChatId());
            for (Participant participant : participants) {
                participantService.deleteParticipant(participant.getParticipantId());
            }
            return ResponseEntity.ok("Chat deleted successfully!");
        }
        throw new Exception("The deletion of the chat is restricted to the owner of the group.");
    }
    @DeleteMapping("/leave/group/{chatId}")
    public ResponseEntity<String> leaveGroupChat(@PathVariable("chatId") UUID chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        chatService.leaveGroupChat(chatId, user.getUserId());
        return ResponseEntity.ok("You left the group");
    }

    @PostMapping("/check")
    public ResponseEntity<UUID> check(@RequestBody List<UserDto> list, @RequestHeader("Authorization") String jwt) throws Exception {
        Chat chat = chatRepository.findSingleChatByUserIds(list.get(0).getUserId(),list.get(1).getUserId());
        if(chat == null){
            throw new Exception("Chat doesn't exist : " + list.get(0).getUserId() + " | " + list.get(1).getUserId());
        }
        return new ResponseEntity<>(chat.getChatId(), HttpStatus.CREATED);
    }
    @PostMapping("group/{chatId}")
    public ResponseEntity<Participant> checkGroup(@PathVariable("chatId") UUID chatId, @RequestBody UserDto userDto, @RequestHeader("Authorization") String jwt) throws Exception {
        Chat chat = chatService.findChatByChatId(chatId);
        User user = userService.findUserById(userDto.getUserId());
        UUID userId = user.getUserId();
        List<Participant> participants = participantService.findAllParticipants(chat.getChatId());
        for (Participant participant : participants) {
            if(participant.getUserId().equals(userId)){
                return new ResponseEntity<>(participant, HttpStatus.OK);
            }
        }
        throw new Exception("Required user is not member of the chat");
    }
}
// Zhando
// eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNTE2NjUyOCwiZXhwIjoxNzE1MjUyOTI4LCJ1c2VybmFtZSI6IlpoYW5kbyJ9.VKNpSjbEJP3g-JjBst4u4MqKDM-6BwmAsWd6s5K2l_Z7xl4ktfWC7PF1UBaN993jBZZvFkG1Xm9Fvv-4u-Z0bw
// Arsen
// eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNTE2NjU0MSwiZXhwIjoxNzE1MjUyOTQxLCJ1c2VybmFtZSI6IkFyc2VuIn0.3TCOP7cyYs8uqAenYlZA22InMfPzbselrc_ecX9kYOqBsEXF9nI6Q7w3bDAsarS_kgGspYt61LOa3pHRfzne_g
// Ars
// eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEaXBlbiIsImlhdCI6MTcxNTE2NjgzMCwiZXhwIjoxNzE1MjUzMjMwLCJ1c2VybmFtZSI6IkFycyJ9.WJf-hTT-zaLCGXCc2azSbU12oEz-0Tf1MTzDq8tM3rXVXrx7OP3U3khoP0PDqfcABXq-B4Qh0KCp60yBc4hV0w