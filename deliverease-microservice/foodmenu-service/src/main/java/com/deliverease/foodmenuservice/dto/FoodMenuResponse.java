package com.deliverease.foodmenuservice.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_skucode")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class FoodMenuResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private boolean isInStock;

}