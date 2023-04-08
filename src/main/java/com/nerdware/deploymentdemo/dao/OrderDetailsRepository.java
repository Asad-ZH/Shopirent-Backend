package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.OrderDetails;
import com.nerdware.deploymentdemo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {


    OrderDetails findByProduct(Product product);
    void deleteByProduct(Product product);
    
}
