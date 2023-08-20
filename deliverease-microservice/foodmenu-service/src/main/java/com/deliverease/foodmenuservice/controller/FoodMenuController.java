package com.deliverease.foodmenuservice.controller;

import com.deliverease.foodmenuservice.dto.FoodMenuResponse;
import com.deliverease.foodmenuservice.menuentity.FoodMenu;
import com.deliverease.foodmenuservice.service.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class FoodMenuController {
    private FoodMenuService foodMenuService;
    @Autowired
    public FoodMenuController(FoodMenuService foodMenuService){

        this.foodMenuService=foodMenuService;
    }

    @PostMapping
    public FoodMenu createFoodMenu(@RequestBody FoodMenu foodMenu){
        return foodMenuService.createFoodMenu(foodMenu);
    }

    @GetMapping
    public ResponseEntity<FoodMenu> getFoodMenu(@PathVariable Long id){
        FoodMenu foodMenu1 = foodMenuService.getFoodMenu(id);
        if(foodMenu1 != null)
            return new ResponseEntity<>(foodMenu1, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //get skuCodes
    @GetMapping("/skucodes")
    public List<FoodMenuResponse> getScuCodes(@RequestParam List<String> skuCode){
        return foodMenuService.isInStock(skuCode);
    }
}
