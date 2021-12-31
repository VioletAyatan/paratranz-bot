package com.para.tranzai.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

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

    public static boolean isJsonStr(String str) {
        try {
            JsonParser.parseString(str);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        boolean jsonStr = isJsonStr("123123");
        System.out.println("jsonStr = " + jsonStr);
    }
}
