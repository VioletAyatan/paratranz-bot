package com.para.tranzai.tools;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * 一个简单的Gson序列化与反序列化工具，对gson方法做了个包装.
 * @author lichengkun
 */
public class GsonUtils {

    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .create();
    }

    public static Object fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json, Class<T> target) {
        return gson.fromJson(json, target);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static String toJson(Object obj, Type typeof) {
        return gson.toJson(obj, typeof);
    }

    public static JsonElement parseToJsonElement(String jsonStr) {
        return JsonParser.parseString(jsonStr);
    }

    public static JsonElement parseToJsonElement(Object obj) {
        return gson.toJsonTree(obj);
    }
}
