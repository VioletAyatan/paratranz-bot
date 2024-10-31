package org.paratranz.bot.api

import cn.hutool.http.*
import com.fasterxml.jackson.core.type.TypeReference
import org.paratranz.bot.tools.JacksonUtil

abstract class AbstractApi protected constructor(private val authorization: String) {
    private fun createRequest(url: String, method: Method): HttpRequest {
        return HttpRequest.of(url)
            .header(Header.AUTHORIZATION, authorization)
            .method(method)
    }

    protected fun doGet(url: String, param: Map<String, Any?>? = null): HttpResponse {
        return createRequest(url, Method.GET)
            .form(param)
            .execute()
    }

    protected fun doGet(url: String): HttpResponse {
        return this.doGet(url, null)
    }

    protected fun doPost(url: String, param: Any? = null): HttpResponse {
        val request = createRequest(url, Method.POST)
        if (param != null) {
            request.body(JacksonUtil.toJson(param))
        }
        return request.execute()
    }

    protected fun doPost(url: String): HttpResponse {
        return this.doPost(url, null)
    }

    protected fun doPut(url: String, param: Any? = null): HttpResponse {
        val request = createRequest(url, Method.PUT)
        if (param != null) {
            request.body(JacksonUtil.toJson(param))
        }
        return request.execute()
    }

    protected fun doDelete(url: String): HttpResponse {
        return createRequest(url, Method.DELETE)
            .execute()
    }

    @Throws(HttpException::class)
    protected fun <T> processResponse(response: HttpResponse, target: Class<T>): T {
        return if (response.isOk) {
            JacksonUtil.fromJson(response.body(), target)
        } else {
            throw HttpException(response.body())
        }
    }

    @Throws(HttpException::class)
    protected fun <T> processResponse(response: HttpResponse, typeReference: TypeReference<T>): T {
        return if (response.isOk) {
            JacksonUtil.fromJson(response.body(), typeReference)
        } else {
            throw HttpException(response.body())
        }
    }
}
