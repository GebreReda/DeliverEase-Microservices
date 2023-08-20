package com.deliverease.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="t_cutomeraccount")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private String accountNumber;
    private int currentBalance;

}
