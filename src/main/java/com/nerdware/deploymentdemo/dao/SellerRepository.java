package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {


}
