package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.Entity.Checkout;
import com.nerdware.deploymentdemo.Entity.OrderDetails;
import com.nerdware.deploymentdemo.dao.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    private CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public Checkout getCheckout(OrderDetails orderDetails) {
        return checkoutRepository.findByOrderDetails(orderDetails);
    }

    public void deleteCheckout(OrderDetails orderDetails) {
        checkoutRepository.deleteByOrderDetails(orderDetails);
    }
}
