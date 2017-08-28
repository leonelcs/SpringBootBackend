package com.leonel.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonel.app.model.Customer;
import com.leonel.app.repository.CustomerRepository;
import com.leonel.app.service.CustomerService;

@Service
public class DefaultCustomerService implements CustomerService {
	
	CustomerRepository repository;
	
	@Autowired
	public DefaultCustomerService(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public Customer save(Customer customer) {
		return repository.save(customer);
	}
	
	public Customer findById(Long id) {
		return repository.findOne(id);
	}
	
	public List<Customer> listAllCustomers() {
		return repository.findAll();
	}

}
