package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Checkout;
import com.nerdware.deploymentdemo.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {


    Checkout findByOrderDetails(OrderDetails orderDetails);

    void deleteByOrderDetails(OrderDetails orderDetails);
}
