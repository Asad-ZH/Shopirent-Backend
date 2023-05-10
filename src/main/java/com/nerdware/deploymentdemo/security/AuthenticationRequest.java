package com.nerdware.deploymentdemo.security;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;

    // getter and setter methods
}
