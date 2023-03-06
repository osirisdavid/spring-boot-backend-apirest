package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.dao;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerDAO extends CrudRepository<Customer, Long> {
}
