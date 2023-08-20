package com.deliverease.customerservice.controller;

import com.deliverease.customerservice.dto.CustomerResponse;
import com.deliverease.customerservice.entity.Customer;
import com.deliverease.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
       return customerService.createCustomer(customer);
    }

    @GetMapping
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer1 = customerService.getCustomer(id);
        if(customer1 != null)
            return new ResponseEntity<>(customer1, HttpStatus.OK);
        else
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }
    //communicate with Order-service
    @GetMapping("/balance")
    public CustomerResponse checkBalance(@RequestParam List<String> customerResponse){
        return customerService.checkCustomer(customerResponse);
    }
}
