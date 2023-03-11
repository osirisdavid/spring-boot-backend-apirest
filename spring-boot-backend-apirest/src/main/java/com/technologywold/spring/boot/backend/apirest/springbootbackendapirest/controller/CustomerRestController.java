package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.controller;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;
import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<?> show(@PathVariable Long id){
        Customer customer = null;
        Map<String, Object> response = new HashMap<>();
        try{
            customer = iCustomerService.finById(id);
        } catch (DataAccessException e){
            response.put("mesage", "Error al realizar consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if(customer == null){
            response.put("mesage", "El cliente ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
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
