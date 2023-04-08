package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
