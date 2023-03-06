package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> findAll();
}
