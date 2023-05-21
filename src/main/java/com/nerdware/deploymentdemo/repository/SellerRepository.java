package com.nerdware.deploymentdemo.repository;

import com.nerdware.deploymentdemo.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUsername(String username);
    Boolean existsByUsername(String username);
    long getById(String username);
}
