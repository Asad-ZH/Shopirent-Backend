package com.nerdware.deploymentdemo.dao;


import com.nerdware.deploymentdemo.Entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {


}
