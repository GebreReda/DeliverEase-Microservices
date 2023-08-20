package com.deliverease.foodmenuservice.menuentity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_menusection")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MenuSection{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String mealType;

    @OneToMany(targetEntity = MenuItems.class, cascade = CascadeType.ALL)
    @JoinColumn(name="menusection", referencedColumnName = "id")
    private List<MenuItems> menuItems;
}