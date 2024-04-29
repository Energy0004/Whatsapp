package com.example.ProjectWhatsapp.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/chat")
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    @GetMapping
    public List<Chat> getChat(){
//        return charRepository.findAll();
        return List.of(new Chat(1,"name",false));
    }
//    record NewCustomerRequest(
//            String name,
//            String email,
//            Integer age
//    ){}
//    @PostMapping
//    public String addCustomer(@RequestBody NewCustomerRequest request){
//        Customer customer = new Customer();
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setAge(request.age());
//        customerRepository.save(customer);
//        return "Customer saved successfully";
//    }
//    @DeleteMapping("{customerId}")
//    public String deleteCustomer(@PathVariable("customerId") Integer id){
//        customerRepository.deleteById(id);
//        return "Customer deleted successfully";
//    }
}