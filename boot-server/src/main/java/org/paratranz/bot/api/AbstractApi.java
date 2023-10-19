package org.paratranz.bot.api;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import org.paratranz.bot.tools.GsonUtil;

import java.util.Map;

public abstract class AbstractApi {
    protected final String authorization;

    protected AbstractApi(String authorization) {
        this.authorization = authorization;
    }

    protected HttpRequest createRequest(String url, Method method) {
        return HttpRequest.of(url)
                .header(Header.AUTHORIZATION, authorization)
                .method(method);
    }

    protected HttpResponse doGet(String url, Map<String, Object> param) {
        return this.createRequest(url, Method.GET)
                .form(param)
                .execute();
    }

    protected HttpResponse doGet(String url) {
        return this.doGet(url, null);
    }

    protected HttpResponse doPost(String url) {
        return this.createRequest(url, Method.POST)
                .execute();
    }

    protected HttpResponse doPost(String url, Object param) {
        return this.createRequest(url, Method.POST)
                .body(GsonUtil.toJson(param))
                .execute();
    }
}
