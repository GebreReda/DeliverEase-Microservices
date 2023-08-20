package com.deliverease.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_ordermenuitems")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderMenuItems {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private String itemName;
    private String itemDescription;
    private int quantity;
    private double price;

}