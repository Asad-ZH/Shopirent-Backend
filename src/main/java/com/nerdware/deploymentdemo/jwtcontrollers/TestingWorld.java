package com.nerdware.deploymentdemo.jwtcontrollers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TestingWorld {
    @GetMapping("/buyer")
    @PreAuthorize("hasAuthority('Buyer')")
    public String helloWorld(){
        return "Hello";
    }
    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('Seller')")
    public String helloWorld2(){
        return " World";
    }
}
