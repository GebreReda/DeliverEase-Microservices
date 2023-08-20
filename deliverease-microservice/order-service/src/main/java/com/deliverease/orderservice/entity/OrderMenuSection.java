package com.deliverease.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_ordermenusection")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderMenuSection {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String mealType;

    @OneToMany(targetEntity = OrderMenuItems.class, cascade = CascadeType.ALL)
    @JoinColumn(name="menusection", referencedColumnName = "id")
    private List<OrderMenuItems> menuItems;
}