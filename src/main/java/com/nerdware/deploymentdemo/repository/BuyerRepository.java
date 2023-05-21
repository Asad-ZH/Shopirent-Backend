package com.nerdware.deploymentdemo.repository;

import com.nerdware.deploymentdemo.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByUsername(String username);
    Boolean existsByUsername(String username);
    long getById(String username);

}
