package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.controller;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;
import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    ICustomerService iCustomerService;

    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> index(){
        return iCustomerService.findAll();
    }

    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public Customer show(@PathVariable Long id){
        return iCustomerService.finById(id);
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody Customer customer){
        return iCustomerService.save(customer);
    }

    @PutMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer update(@RequestBody Customer customer, @PathVariable Long id){
        Customer customerDB = iCustomerService.finById(id);

        customerDB.setName(customer.getName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        return iCustomerService.save(customerDB);
    }

    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        iCustomerService.delete(id);
    }
}
