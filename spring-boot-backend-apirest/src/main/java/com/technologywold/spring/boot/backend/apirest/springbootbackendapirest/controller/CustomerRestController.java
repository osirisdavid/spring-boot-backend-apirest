package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.controller;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;
import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    ICustomerService iCustomerService;

    @GetMapping("/customer")
    public List<Customer> index(){
        return iCustomerService.findAll();
    }
}
