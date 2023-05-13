package com.nerdware.deploymentdemo.repository;

import com.nerdware.deploymentdemo.models.UserEntity;
import com.nerdware.deploymentdemo.models.UserEntity2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository2 extends JpaRepository<UserEntity2, Integer> {
    Optional<UserEntity2> findByUsername(String username);
    Boolean existsByUsername(String username);
}
