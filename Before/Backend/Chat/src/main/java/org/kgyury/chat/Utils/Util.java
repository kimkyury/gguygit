package org.kgyury.chat.Utils;


import java.util.UUID;

public class Util {
    public static String generatedUserId() {
        return UUID.randomUUID().toString();
    }
}
