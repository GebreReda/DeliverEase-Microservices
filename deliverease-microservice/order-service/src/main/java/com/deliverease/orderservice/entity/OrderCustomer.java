package com.deliverease.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_ordercustomer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderCustomer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //basic information
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
    private String role;
}
