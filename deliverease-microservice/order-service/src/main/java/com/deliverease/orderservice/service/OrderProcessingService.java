package com.deliverease.orderservice.service;

import com.deliverease.customerservice.dto.CustomerResponse;
import com.deliverease.foodmenuservice.dto.FoodMenuResponse;
import com.deliverease.orderservice.entity.CustomerOrder;
import com.deliverease.orderservice.entity.OrderMenuItems;
import com.deliverease.orderservice.entity.OrderMenuSection;
import com.deliverease.orderservice.entity.OrderStatus;
import com.deliverease.orderservice.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderProcessingService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    public OrderProcessingService(CustomerOrderRepository customerOrderRepository){
        this.customerOrderRepository=customerOrderRepository;
    }
    public CustomerOrder processOrder(OrderStatus orderStatus){
        String on = orderStatus.getOrderNumber();
        CustomerOrder customerOrder = customerOrderRepository.findByOrderNumber(orderStatus.getOrderNumber());
        int totalCost=0;
        if(customerOrder!=null){
            System.out.println(customerOrder.getOrderNumber());
            //check if items aree in stoke
            displayAllFields(customerOrder);
            displayAllFieldsJavaEight(customerOrder);
            //Chekc if items exist in stoke
            if(!customerOrder.isProcessed()){
                System.out.println("Processing order with order number "+customerOrder.getOrderNumber());
                List<String> orderSkuCodes= new ArrayList<>();
                //Customer OrderMenuSection
                List<OrderMenuSection> orderMenuSectionsList = customerOrder.getOrderMenuSection();
                for(OrderMenuSection orderMenuSection: orderMenuSectionsList){
                    //Customer OrderMenuItems
                    List<OrderMenuItems> orderMenuItemsList = orderMenuSection.getMenuItems();
                    for(OrderMenuItems orderMenuItems: orderMenuItemsList){
                        totalCost += orderMenuItems.getPrice() * orderMenuItems.getQuantity();
                        orderSkuCodes.add(orderMenuItems.getSkuCode());

                    }
                    System.out.println("Ordered items have skucodes: "+orderSkuCodes);
                }
                System.out.println("Before calling FoodMenu");
                //check if items ordered are in stock
                FoodMenuResponse[] foodMenuResponseArray = webClientBuilder.build().get()
                        .uri("http://foodmenu-service/api/menu/skucodes",
                                uriBuilder->uriBuilder.queryParam("skuCode",orderSkuCodes).build())
                        .retrieve()
                        .bodyToMono(FoodMenuResponse[].class)
                        .block();

                System.out.println("After calling FoodMenu");
                boolean allItemsInStock = Arrays.stream(foodMenuResponseArray)
                        .allMatch(FoodMenuResponse::isInStock);
                if(allItemsInStock){
                    System.out.println("All items exist");
                    calculateTotalCost(customerOrder, totalCost);
                    customerOrder.setProcessed(true);

                }
                else{
                    throw new RuntimeException("Items are unavailable, come latter!!");
                }

            }
            return customerOrder;
        }
       else return null;
    }
    private void calculateTotalCost(CustomerOrder customerOrder, int totalCost){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setAmount(totalCost);
        String orderNumber = customerOrder.getOrderNumber();
        String firstName = customerOrder.getOrderCustomer().getFirstName();
        String lastName = customerOrder.getOrderCustomer().getLastName();
//        System.out.println("First name is: "+firstName);
//        System.out.println("First name is: "+lastName);
        customerResponse.setFirstName(firstName);
        customerResponse.setLastName(lastName);

        List<String> cr = new ArrayList<>();
        cr.add(firstName);
        cr.add(lastName);
        cr.add(String.valueOf(totalCost));

        CustomerResponse result = webClientBuilder.build().get()
                .uri("http://customer-service/api/customer/balance",
                        uriBuilder -> uriBuilder.queryParam("customerResponse", cr).build())
                .retrieve()
                .bodyToMono(CustomerResponse.class)
                .block();
        if(result.getStatus()==1){
            System.out.println("Customer is unavailable");
            throw new RuntimeException("No customer is available");
        }
        else if(result.getStatus()==2){
            System.out.println("Customer has no enough balance");
            CustomerOrder customerOrder1 = customerOrderRepository.findByOrderNumber(orderNumber);
            customerOrder1.setCustomerId(result.getCustomerId());
            customerOrderRepository.save(customerOrder1);
            throw new RuntimeException("Insufficient funds");

        }
        else if(result.getStatus()==3){
            System.out.println("Customer is available and has enough balance");
            System.out.println("Custome Id is: "+result.getCustomerId());
            CustomerOrder customerOrder1 = customerOrderRepository.findByOrderNumber(orderNumber);
            customerOrder1.setCustomerId(result.getCustomerId());
            customerOrderRepository.save(customerOrder1);
        }
        else{
            System.out.println("Unknown exception");
            throw new RuntimeException("Exception");
        }
    }
    private void displayAllFields(CustomerOrder customerOrder){
        System.out.println(customerOrder.getOrderNumber());
        //check account status
        System.out.println(customerOrder.isProcessed());
        System.out.println(customerOrder.isPicked());
        System.out.println(customerOrder.isDelivered());
        //Status check
        System.out.println(customerOrder.isProcessed());
        System.out.println(customerOrder.isPicked());
        System.out.println(customerOrder.isDelivered());
        //Customer Basic info
        System.out.println(customerOrder.getOrderCustomer().getFirstName());
        System.out.println(customerOrder.getOrderCustomer().getLastName());
        System.out.println(customerOrder.getOrderCustomer().getEmail());
        System.out.println(customerOrder.getOrderCustomer().getTelephoneNumber());
        System.out.println(customerOrder.getOrderCustomer().getRole());
        //Customer OrderMenuSectioin
        List<OrderMenuSection> orderMenuSectionsList = customerOrder.getOrderMenuSection();
        for(OrderMenuSection orderMenuSection: orderMenuSectionsList){
            System.out.println(orderMenuSection.getMealType());
            //Customer OrderMenuItems
            List<OrderMenuItems> orderMenuItemsList = orderMenuSection.getMenuItems();
            for(OrderMenuItems orderMenuItems: orderMenuItemsList){
                System.out.println(orderMenuItems.getItemName());
                System.out.println(orderMenuItems.getItemDescription());
                System.out.println(orderMenuItems.getQuantity());
                System.out.println(orderMenuItems.getPrice());
            }
        }
    }
    private void displayAllFieldsJavaEight(CustomerOrder customerOrder){
    }
}
