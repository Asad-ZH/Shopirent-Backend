package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    Optional<Buyer> findByUsername(String username);

}
