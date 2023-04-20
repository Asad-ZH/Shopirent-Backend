package com.nerdware.deploymentdemo.security;

public enum ApplicationUserPermission {
    BUYER_READ("buyer:read"),
    BUYER_WRITE("buyer:write"),
    SELLER_READ("seller:read"),
    SELLER_WRITE("seller:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
