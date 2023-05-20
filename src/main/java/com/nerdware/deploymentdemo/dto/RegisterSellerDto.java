package com.nerdware.deploymentdemo.dto;

import lombok.Data;

@Data
public class RegisterSellerDto {
    private String username;
    private String password;
    private String name;
    private String storeName;
    private String phone;
    private String address;
    private String city;
    private String zipCode;

}

