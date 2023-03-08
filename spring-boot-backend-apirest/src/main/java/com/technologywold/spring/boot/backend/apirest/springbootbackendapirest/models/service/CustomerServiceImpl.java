package com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.service;

import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.dao.ICustomerDAO;
import com.technologywold.spring.boot.backend.apirest.springbootbackendapirest.models.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private ICustomerDAO iCustomerDAO;
    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>)iCustomerDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customer finById(Long id) {
        return iCustomerDAO.findById(id).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return iCustomerDAO.save(customer);
    }

    @Override
    public void delete(Long id) {
        iCustomerDAO.deleteById(id);
    }
}
