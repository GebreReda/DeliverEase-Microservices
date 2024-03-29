package com.deliverease.foodmenuservice.menuentity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_restlocation")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RestLocation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String state;
    private String city;
    private String zip;
}
