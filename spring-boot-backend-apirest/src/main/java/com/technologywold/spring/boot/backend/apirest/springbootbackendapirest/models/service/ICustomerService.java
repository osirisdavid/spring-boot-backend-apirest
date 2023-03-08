package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> findAll();

    public Customer finById(Long id);
    public Customer save(Customer customer);
    public void delete (Long id);
}
