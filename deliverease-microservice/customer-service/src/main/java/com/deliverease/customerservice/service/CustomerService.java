package com.deliverease.customerservice.service;

import com.deliverease.customerservice.dto.CustomerResponse;
import com.deliverease.customerservice.entity.Customer;
import com.deliverease.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerResponse checkCustomer(List<String> customerResponse){
//        System.out.println("This is checkCustomer Method");
//        System.out.println(customerResponse.get(0));
//        System.out.println(customerResponse.get(1));
//        System.out.println(customerResponse.get(2));

        Customer customer = customerRepository.findByFirstNameAndLastName(customerResponse.get(0), customerResponse.get(1));
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
        CustomerResponse cr = new CustomerResponse();
        if(customer == null) {
            cr.setStatus(1);
            return cr;
        }
        else{
            cr.setCustomerId(customer.getId());
            cr.setStatus(2);
            int totalAmount = Integer.parseInt(customerResponse.get(2));
            if(customer.getCustomerAccount().getCurrentBalance() >= Integer.parseInt(customerResponse.get(2))){
                cr.setStatus(3);
                int currentBalance = customer.getCustomerAccount().getCurrentBalance();
                currentBalance = currentBalance - totalAmount;
                customer.getCustomerAccount().setCurrentBalance(currentBalance);
                customerRepository.save(customer);
                System.out.println("Customer balancce has been updated");
                return cr;
            }
            else return cr;
        }
    }
}
