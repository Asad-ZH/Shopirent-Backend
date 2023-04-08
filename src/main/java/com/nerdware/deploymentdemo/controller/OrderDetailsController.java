package com.nerdware.deploymentdemo.controller;


import com.nerdware.deploymentdemo.Entity.OrderDetails;
import com.nerdware.deploymentdemo.Entity.Product;
import com.nerdware.deploymentdemo.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orderdetails")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public OrderDetails getOrderDetails(@RequestBody Product product) {
        return orderDetailsService.getOrderDetails(product);
    }

    @DeleteMapping
    public void deleteOrderDetails(@RequestBody Product product) {
        orderDetailsService.deleteOrderDetails(product);
    }

}
