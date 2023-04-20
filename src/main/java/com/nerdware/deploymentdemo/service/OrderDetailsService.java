package com.nerdware.deploymentdemo.service;


import com.nerdware.deploymentdemo.Entity.OrderDetails;
import com.nerdware.deploymentdemo.Entity.Product;
import com.nerdware.deploymentdemo.dao.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public OrderDetails getOrderDetails(Product product) {
        return orderDetailsRepository.findByProduct(product);
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }
}
