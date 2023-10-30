package org.paratranz.bot.api;

import cn.hutool.http.*;
import com.google.gson.reflect.TypeToken;
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
        return this.doPost(url, null);
    }

    protected HttpResponse doPost(String url, Object param) {
        HttpRequest request = this.createRequest(url, Method.POST);
        if (param != null) {
            request.body(GsonUtil.toJson(param));
        }
        return request.execute();
    }

    protected HttpResponse doPut(String url, Object param) {
        HttpRequest request = this.createRequest(url, Method.PUT);
        if (param != null) {
            request.body(GsonUtil.toJson(param));
        }
        return request.execute();
    }

    protected HttpResponse doDelete(String url) {
        return this.createRequest(url, Method.DELETE)
                .execute();
    }

    protected <T> T processResponse(HttpResponse response, Class<T> target) throws HttpException {
        if (response.isOk()) {
            return GsonUtil.fromJson(response.body(), target);
        } else {
            throw new HttpException(response.body());
        }
    }

    protected <T> T processResponse(HttpResponse response, TypeToken<T> typeToken) throws HttpException {
        if (response.isOk()) {
            return GsonUtil.fromJson(response.body(), typeToken);
        } else {
            throw new HttpException(response.body());
        }
    }
}
