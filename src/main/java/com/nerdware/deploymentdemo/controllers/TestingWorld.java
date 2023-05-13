package com.nerdware.deploymentdemo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingWorld {

    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('UserEntity2')")
    public String helloWorld(){
        return "Hello";
    }
    @GetMapping("/buyer")
    @PreAuthorize("hasAuthority('UserEntity')")
    public String helloWorld2(){
        return " World";
    }
}
