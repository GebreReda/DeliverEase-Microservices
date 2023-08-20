package com.deliverease.orderservice.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_customeresponse")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int amount;
    //1-customer unavailable
    //2-customer available but no enough balance
    //3-customer available and has enough balance
    private int status;
    private long customerId;
}