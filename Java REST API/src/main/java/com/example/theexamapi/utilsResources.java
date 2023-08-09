package com.example.theexamapi;

import java.util.UUID;

public class utilsResources {
    public static String generateUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
