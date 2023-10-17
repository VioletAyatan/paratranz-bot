package org.paratranz.bot.api;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;

import java.util.Map;

public abstract class AbstractApi {
    protected final String authorization;

    protected AbstractApi(String authorization) {
        this.authorization = authorization;
    }

    protected HttpRequest createRequest(String url, Method method) {
        return new HttpRequest(url)
                .header(Header.AUTHORIZATION, authorization)
                .method(method);
    }

    protected String doGet(String url, Map<String, Object> param) {
        return this.createRequest(url, Method.GET)
                .form(param)
                .execute()
                .body();
    }

    protected String doGet(String url) {
        return this.doGet(url, null);
    }
}
