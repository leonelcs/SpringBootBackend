package com.leonel.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.leonel.app.model.Customer;
import com.leonel.app.model.Note;
import com.leonel.app.service.CustomerService;
import com.leonel.app.service.NoteService;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class AppController {
 
    public static final Logger logger = LoggerFactory.getLogger(AppController.class);
 
    @Autowired
    CustomerService customerService;
 
    @Autowired
    NoteService noteService;

 
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.listAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable("id") long id) {
        logger.info("Fetching Customer with id {}", id);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/customers/", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Customer : {}", customer);
 
        customerService.save(customer);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @ResponseBody
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
        logger.info("Updating Customer with id {}", id);
 
        Customer currentCustomer = customerService.findById(id);
 
        if (currentCustomer == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
 
        currentCustomer.setName(customer.getName());
        currentCustomer.setEmail(customer.getEmail());
        currentCustomer.setNotes(customer.getNotes());
        currentCustomer.setStatus(customer.getStatus());
        currentCustomer.setPhone(customer.getPhone());
 
        customerService.save(currentCustomer);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = "/customers/{id}/notes", method = RequestMethod.GET)
    public ResponseEntity<List<Note>> listAllNotesByCustomers(@PathVariable("id") Long id) {
        logger.info("Fetching Customer with id {}", id);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<List<Note>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Note>>(customer.getNotes(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/customers/{id}/notes/", method = RequestMethod.POST)
    public ResponseEntity<?> createNote(@PathVariable("id") Long id, @RequestBody Note note, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Note : {}", note);
 
        Customer c = customerService.findById(id);
        note.setCustomer(c);
        c.getNotes().add(note);
        c.setName("Teste");
        customerService.save(c);
        noteService.save(note);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customers/{id}/notes/").buildAndExpand(c.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

}
