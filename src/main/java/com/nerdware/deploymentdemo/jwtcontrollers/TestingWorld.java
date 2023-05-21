package com.nerdware.deploymentdemo.jwtcontrollers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingWorld {

    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('Buyer')")
    public String helloWorld(){
        return "Hello";
    }
    @GetMapping("/buyer")
    @PreAuthorize("hasAuthority('Seller')")
    public String helloWorld2(){
        return " World";
    }
}
