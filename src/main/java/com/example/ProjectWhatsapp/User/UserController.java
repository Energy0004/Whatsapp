package com.example.ProjectWhatsapp.User;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;
    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
//        return List.of(new User(1, "Alish", "ppp", "993933", LocalDate.of(2000, Month.JANUARY, 5), "acitve"));
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.addUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
// {
//     "username": "Alish",
//     "password": "pps",
//     "phoneNumber": "993933",
//     "lastLogin": "2020-11-11",
//     "status": "active"
// }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!.");
    }
//    @PostMapping
//    public String createUser(@RequestBody User user) throws Exception {
//        String number = user.getPhoneNumber();
//        User isUser = userRepository.findByPhoneNumber(number);
//        if(isUser != null){
//            throw new Exception("Phone number is used with another account");
//        }
//        User newUser = new User();
//        user.setUsername(user.getUsername());
//        user.setPassword(user.getPassword());
//        user.setPhoneNumber(user.getPhoneNumber());
//        user.setLastLogin(user.getLastLogin());
//        user.setStatus(user.getStatus());
//        userRepository.save(newUser);
//        return "User saved successfully";
//    }
//    @DeleteMapping("{customerId}")
//    public String deleteCustomer(@PathVariable("customerId") Integer id){
//        customerRepository.deleteById(id);
//        return "Customer deleted successfully";
//    }
}