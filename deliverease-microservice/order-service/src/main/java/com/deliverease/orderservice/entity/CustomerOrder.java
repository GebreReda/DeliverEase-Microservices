package com.deliverease.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_customerorder")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private boolean processed;
    private boolean picked;
    private boolean delivered;
    private Long customerId;

    @ManyToOne(targetEntity = OrderCustomer.class, cascade = CascadeType.ALL)
    @JoinColumn(name="ordercustomer",referencedColumnName = "id")
    private OrderCustomer orderCustomer;

    @OneToMany(targetEntity = OrderMenuSection.class, cascade = CascadeType.ALL)
    @JoinColumn(name="customerorder",referencedColumnName = "id")
    private List<OrderMenuSection> orderMenuSection;
}
