package com.deliverease.foodmenuservice.repository;

import com.deliverease.foodmenuservice.menuentity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {
}
