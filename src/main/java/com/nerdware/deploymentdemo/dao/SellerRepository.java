package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByUsername(String username);
    Seller getById(Long sellerId);
}
