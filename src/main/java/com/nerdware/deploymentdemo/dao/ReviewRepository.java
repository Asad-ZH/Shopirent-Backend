package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
