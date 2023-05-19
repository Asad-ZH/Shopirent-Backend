package com.nerdware.deploymentdemo.repository;

import com.nerdware.deploymentdemo.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    int getById(String username);
}
