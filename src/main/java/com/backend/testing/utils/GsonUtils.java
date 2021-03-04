package com.backend.testing.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public final class GsonUtils {
    private static final Gson gson = new Gson();

    private GsonUtils() {
        throw new IllegalStateException("This is utility class!");
    }

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
