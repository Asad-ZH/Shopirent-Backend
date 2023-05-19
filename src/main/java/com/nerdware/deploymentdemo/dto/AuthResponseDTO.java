package com.nerdware.deploymentdemo.dto;

import com.nerdware.deploymentdemo.models.Role;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String username;
    private int userId;
    private String role;

    public AuthResponseDTO(String accessToken, String username, int userId, String role) {
        this.accessToken = accessToken;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

}
