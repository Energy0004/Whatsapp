package com.example.ProjectWhatsapp.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("{userId}")
    public ResponseEntity<UserInfo> getUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserById(userId);
        UserInfo userInfo = new UserInfo(user.getUsername(), userId);
        return new ResponseEntity<>(userInfo, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!.");
    }
    //    @GetMapping
//    public List<User> getUsers(){
//        return userRepository.findAll();
//    }
//    @PostMapping
//    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
//        UserDto savedUser = userService.addUser(userDto);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//// {
////     "username": "Alish",
////     "password": "pps",
////     "lastLogin": "2020-11-11",
////     "status": "active"
//// }
//    }
}