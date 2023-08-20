package com.deliverease.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="t_orderstatus")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private boolean processed;
    private boolean picked;
    private boolean delivered;
}