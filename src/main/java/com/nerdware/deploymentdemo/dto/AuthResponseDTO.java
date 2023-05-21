package com.nerdware.deploymentdemo.dto;

import com.nerdware.deploymentdemo.models.Role;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String username;
    private long userId;
    private String role;

    public AuthResponseDTO(String accessToken, String username, long userId, String role) {
        this.accessToken = accessToken;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

}
