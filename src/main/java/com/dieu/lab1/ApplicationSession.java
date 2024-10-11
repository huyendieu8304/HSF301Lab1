package com.dieu.lab1;

import java.util.HashMap;
import java.util.Map;

public class ApplicationSession {
    public static Map<String, Object> applicationSession = new HashMap<String, Object>();

    public static void addAttribute(String key, Object value) {
        applicationSession.put(key, value);
    }

    public static Object getAttribute(String key) {
        return applicationSession.get(key);
    }

    public static void clearApplicationSesion() {
        applicationSession.clear();
    }
}
