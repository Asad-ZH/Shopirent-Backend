package com.nerdware.deploymentdemo.controllers;

import java.util.Set;
import java.util.HashSet;

public class JwtTokenBlacklist {
    private static final Set<String> tokens = new HashSet<>();

    public static void add(String token) {
        tokens.add(token);
    }

    public static boolean contains(String token) {
        return tokens.contains(token);
    }

    public static void remove(String token) {
        tokens.remove(token);
    }
}
