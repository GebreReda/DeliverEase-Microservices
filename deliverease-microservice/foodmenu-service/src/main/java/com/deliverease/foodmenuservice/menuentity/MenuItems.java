package com.deliverease.foodmenuservice.menuentity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_menuitems")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MenuItems{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private String itemName;
    private String itemDescription;
    private int quantity;
    private double price;

}