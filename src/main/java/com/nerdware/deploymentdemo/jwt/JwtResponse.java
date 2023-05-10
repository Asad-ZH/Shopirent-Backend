package com.nerdware.deploymentdemo.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final Long id;
    private final String username;

    public JwtResponse(String jwttoken, Long id, String username) {
        this.jwttoken = jwttoken;
        this.id = id;
        this.username = username;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }
}
