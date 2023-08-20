package com.deliverease.customerservice.repository;

import com.deliverease.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstNameAndLastName(String firstname, String lastname);
}
