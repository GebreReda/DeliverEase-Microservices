package com.deliverease.orderservice.controller;

import com.deliverease.orderservice.entity.CustomerOrder;
import com.deliverease.orderservice.entity.OrderStatus;
import com.deliverease.orderservice.service.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/processing")
public class OrderProcessingController {
    private OrderProcessingService orderProcessingService;
    @Autowired
    public OrderProcessingController(OrderProcessingService orderProcessingService){
        this.orderProcessingService=orderProcessingService;
    }
    @PostMapping
    public ResponseEntity<CustomerOrder> processOrder(@RequestBody OrderStatus orderStatus){
        CustomerOrder customerOrder = orderProcessingService.processOrder(orderStatus);
        if(customerOrder!=null)
            return new ResponseEntity<>(customerOrder, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
