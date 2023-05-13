package com.nerdware.deploymentdemo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingWorld {

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public String helloWorld(){
        return "Hello";
    }
    @GetMapping("/buyer")
    @PreAuthorize("hasRole('BUYER')")
    public String helloWorld2(){
        return " World";
    }
}
