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
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(customer == null){
            response.put("mesage", "El cliente ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping("/customer")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Customer customer){
        Customer customerNew = null;
        Map<String, Object> response = new HashMap<>();
        try{
            customerNew = iCustomerService.save(customer);
        } catch (DataAccessException e){
            response.put("mesage", "Error al realizar el INSERT en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mesage", "El cliente ha sido creado con éxito");
        response.put("customer", customerNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable Long id){
        Customer customerDB = iCustomerService.finById(id);
        Customer customerUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if(customerDB == null){
            response.put("mesage", "Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            customerDB.setName(customer.getName());
            customerDB.setLastName(customer.getLastName());
            customerDB.setEmail(customer.getEmail());
            customerUpdate = iCustomerService.save(customerDB);
        } catch (DataAccessException e){
            response.put("mesage", "Error al actualizar el cliente en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mesage", "El cliente ha sido actualizado con exito!");
        response.put("mesage", customerUpdate);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        iCustomerService.delete(id);
    }
}
