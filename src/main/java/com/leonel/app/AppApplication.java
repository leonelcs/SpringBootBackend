package com.leonel.app;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.leonel.app.model.Customer;
import com.leonel.app.model.CustomerStatus;
import com.leonel.app.model.Note;
import com.leonel.app.repository.CustomerRepository;
import com.leonel.app.repository.NoteRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(CustomerRepository repository, NoteRepository noteRepository) {
		return (args) -> {
			// save a couple of customers
			Customer c1 = new Customer();
			c1.setEmail("leonecs@gmail.com");
			c1.setName("Leonel");
			c1.setPhone("+55(11)97433-0774");
			c1.setStatus(CustomerStatus.CURRENT);
			c1.setCreation_date(LocalDate.now());
			Note n1 = new Note();
			n1.setCustomer(c1);
			n1.setComment("first comment");
			repository.save(c1);
			noteRepository.save(n1);

		};
	}
}
