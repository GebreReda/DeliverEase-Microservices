package com.deliverease.foodmenuservice.service;

import com.deliverease.foodmenuservice.dto.FoodMenuResponse;
import com.deliverease.foodmenuservice.menuentity.FoodMenu;
import com.deliverease.foodmenuservice.menuentity.MenuItems;
import com.deliverease.foodmenuservice.menuentity.MenuSection;
import com.deliverease.foodmenuservice.repository.FoodMenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodMenuService {
    private FoodMenuRepository menuRepository;
    @Autowired
    public FoodMenuService(FoodMenuRepository menuRepository){
        this.menuRepository=menuRepository;
    }
    public FoodMenu createFoodMenu(FoodMenu foodMenu){
        return menuRepository.save(foodMenu);
    }
    public FoodMenu getFoodMenu(Long id){
        return menuRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<FoodMenuResponse> isInStock(List<String> skuCode){
//        return menuRepository.findBySkucodeIn(skuCode)
//                .stream()
//                .map(menu->FoodMenuResponse.builder()
//                        .skuCode(menu.))
        System.out.println("Hello: ");
        List<FoodMenuResponse> foodMenuResponseList = new ArrayList<>();
        List<String> skucodelist = new ArrayList<>();
        List<FoodMenu> foodMenuList = menuRepository.findAll();
        displayAllFields(foodMenuList);
        for(FoodMenu fml: foodMenuList){
            for(MenuSection ms: fml.getMenuSection()){
                for(MenuItems mi: ms.getMenuItems()){
                    System.out.println("SkuCode available is: "+mi.getSkuCode());
                    if(skuCode.contains(mi.getSkuCode())) {
                        System.out.println("SkuCode found is: "+mi.getSkuCode());
                        FoodMenuResponse foodMenuResponse = new FoodMenuResponse();
                        foodMenuResponse.setInStock(true);
                        foodMenuResponse.setSkuCode(mi.getSkuCode());

                        foodMenuResponseList.add(foodMenuResponse);
                    }
                }
            }
        }
        System.out.println("This is FoodMenu Microservice and FoodMenuResponse list is: "+foodMenuResponseList);
        return foodMenuResponseList;
    }
    private void displayAllFields(List<FoodMenu> foodMenuList) {
        for (FoodMenu fm : foodMenuList) {
            List<MenuSection> menuSectionsList = fm.getMenuSection();
            for (MenuSection ms : menuSectionsList) {

                //Customer OrderMenuItems
                List<MenuItems> menuItemsList = ms.getMenuItems();
                for (MenuItems mi : menuItemsList) {
                    System.out.println(mi.getSkuCode());
                }
            }
        }
    }

}
