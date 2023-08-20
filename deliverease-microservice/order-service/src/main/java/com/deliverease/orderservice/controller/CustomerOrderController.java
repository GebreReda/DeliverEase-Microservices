package com.deliverease.orderservice.controller;

import com.deliverease.orderservice.entity.CustomerOrder;
import com.deliverease.orderservice.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {
    private CustomerOrderService customerOrderService;
    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService){
        this.customerOrderService=customerOrderService;
    }
    @PostMapping
    public ResponseEntity<CustomerOrder> createCustomerOrder(@RequestBody CustomerOrder customerOrder){
        CustomerOrder customerOrder1 = customerOrderService.createCustomerOrder(customerOrder);
        if(customerOrder1!=null)
            return new ResponseEntity<>(customerOrder1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
