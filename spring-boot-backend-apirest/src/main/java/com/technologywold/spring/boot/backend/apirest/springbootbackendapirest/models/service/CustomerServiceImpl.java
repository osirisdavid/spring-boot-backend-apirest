package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.dao.ICustomerDAO;
import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private ICustomerDAO iCustomerDAO;
    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>)iCustomerDAO.findAll();
    }
}
