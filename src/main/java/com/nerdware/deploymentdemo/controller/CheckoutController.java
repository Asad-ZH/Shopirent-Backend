package com.nerdware.deploymentdemo.controller;


import com.nerdware.deploymentdemo.Entity.Checkout;
import com.nerdware.deploymentdemo.Entity.OrderDetails;
import com.nerdware.deploymentdemo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping
    public Checkout getCheckout(@RequestBody OrderDetails orderDetails) {
        return checkoutService.getCheckout(orderDetails);
    }

    @DeleteMapping
    public void deleteCheckout(@RequestBody OrderDetails orderDetails) {
        checkoutService.deleteCheckout(orderDetails);
    }
}
