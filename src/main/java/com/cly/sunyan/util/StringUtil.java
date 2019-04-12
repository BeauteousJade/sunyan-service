package com.cly.sunyan.util;

import java.util.UUID;

public class StringUtil {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
