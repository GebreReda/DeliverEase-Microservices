package com.deliverease.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="t_cutomeraddress")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String state;
    private String city;
    private String zip;

}
