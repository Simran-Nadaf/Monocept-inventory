package com.techlabs.inventorymanagement.service;



import java.util.UUID;

public class IDGenerator {
    public static String generateID() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate an 8-character ID
    }
}

