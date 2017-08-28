package com.leonel.app.service;

import java.util.List;

import com.leonel.app.model.Customer;

public interface CustomerService {
	

	public Customer save(Customer customer);
	
	public Customer findById(Long id);
	
	public List<Customer> listAllCustomers();
	
	

}
