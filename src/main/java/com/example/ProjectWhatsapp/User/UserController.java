package com.example.ProjectWhatsapp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
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