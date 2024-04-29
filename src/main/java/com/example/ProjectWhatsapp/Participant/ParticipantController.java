package com.example.ProjectWhatsapp.Participant;

import com.example.ProjectWhatsapp.Chat.Chat;
import com.example.ProjectWhatsapp.Chat.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/participant")
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;
    @GetMapping
    public List<Participant> getParticipant(){
//        return participantRepository.findAll();
        return List.of(new Participant(1, 2, 3, LocalDate.of(1, Month.JANUARY, 23)));
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