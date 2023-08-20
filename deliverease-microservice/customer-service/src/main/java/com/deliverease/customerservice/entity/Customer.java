package com.deliverease.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_customer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //basic information
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
    private String role;
    //Address
    @OneToOne(targetEntity = CustomerAddress.class, cascade = CascadeType.ALL)
    @JoinColumn(name="customeraddress", referencedColumnName = "id")
    private CustomerAddress customerAddress;

    @OneToOne(targetEntity = CustomerAccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name="customeraccount", referencedColumnName = "id")
    private CustomerAccount customerAccount;
}
