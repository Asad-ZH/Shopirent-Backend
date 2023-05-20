package com.nerdware.deploymentdemo.dto;

import lombok.Data;

@Data
public class RegisterBuyerDto {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String address;

}
