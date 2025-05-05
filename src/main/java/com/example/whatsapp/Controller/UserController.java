package com.example.whatsapp.Controller;

import com.example.whatsapp.Exception.UserException;
import com.example.whatsapp.Modal.User;
import com.example.whatsapp.Request.UpdateUserRequest;
import com.example.whatsapp.Service.Response.ApiResponse;
import com.example.whatsapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfile(jwt);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String query){
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfile(jwt);
        userService.updateUser(user.getId(), req);
        ApiResponse apiResponse = new ApiResponse("User updated successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}