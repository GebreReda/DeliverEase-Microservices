package com.deliverease.foodmenuservice.menuentity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_foodmenu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class FoodMenu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String restName;

    @OneToOne(targetEntity = RestLocation.class, cascade = CascadeType.ALL)
    @JoinColumn(name="restlocation",referencedColumnName = "id")
    private RestLocation restLocation;

    @OneToMany(targetEntity = MenuSection.class, cascade = CascadeType.ALL)
    @JoinColumn(name="foodmenu",referencedColumnName = "id")
    private List<MenuSection> menuSection;
}
