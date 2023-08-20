package com.deliverease.orderservice.service;

import com.deliverease.orderservice.entity.CustomerOrder;
import com.deliverease.orderservice.event.OrderPlacedEvent;
import com.deliverease.orderservice.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerOrderService {
    @Autowired
    private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    public CustomerOrderService(CustomerOrderRepository customerOrderRepository){
        this.customerOrderRepository=customerOrderRepository;
    }

    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder){
        System.out.println("Order is Placed");
        customerOrder.setOrderNumber(UUID.randomUUID().toString());
        CustomerOrder customerOrder1 = customerOrderRepository.save(customerOrder);
        System.out.println("Sending customer order number "+customerOrder1.getOrderNumber());
        kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(customerOrder1.getOrderNumber()));
        return customerOrder1;
    }

    public CustomerOrder getCustomerOrder(Long id){
        return customerOrderRepository.findById(id).orElse(null);
    }
}
