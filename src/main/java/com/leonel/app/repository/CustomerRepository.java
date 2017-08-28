package com.leonel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leonel.app.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	

}
