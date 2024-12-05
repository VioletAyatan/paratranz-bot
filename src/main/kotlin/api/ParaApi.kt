package com.example.api

import cn.hutool.http.HttpRequest
import cn.hutool.http.HttpUtil
import com.example.api.entity.PageResult
import com.example.api.entity.ProjectApplication
import com.example.api.entity.TestContent
import com.example.tools.JacksonUtil
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

/**
 * Paratranz网站API简单封装
 */
object ParaApi {
    /**
     * paratranz网站API地址
     */
    private const val PARATRANZ_API = "https://paratranz.cn/api"

    /**
     * API授权key，访问一些接口时需要key才可以调用
     *
     * 首先需要获取API Token，可以在个人资料页面中的设置选项卡获取API Token， 调用 API 时将 Token 直接放在请求头的 Authorization 中即可。
     */
    private var authorizationKey = ""

    /**
     * 设置Authorization
     * @param authorizationKey 授权key
     */
    fun setAuthorization(authorizationKey: String) {
        this.authorizationKey = authorizationKey
    }

    /**
     * 获取Authorization
     */
    fun getAuthorizationKey(): String {
        return this.authorizationKey
    }

    /**
     * 与项目`申请`有关的接口
     */
    val application: Application = Application()


    class Application {
        /**
         * 获取项目申请列表
         * @param projectId 项目id
         * @param status 申请状态，不传为所有（0待审核 2已通过 1已拒绝）
         * @param page 页码
         * @param pageSize 页数
         */
        fun getProjectApplications(
            projectId: Int,
            status: Int? = null,
            page: Int = 1,
            pageSize: Int = 50
        ): PageResult<ProjectApplication> {
            val response = createGet(
                url = "$PARATRANZ_API/projects/$projectId/applications",
                paramMap = mapOf(
                    "page" to page,
                    "pageSize" to pageSize,
                    "status" to status
                )
            ).execute()
            if (response.isOk) {
                return JacksonUtil.fromJson(response.body(), jacksonTypeRef<PageResult<ProjectApplication>>())
            }
            TODO("Todo....")
        }

        /**
         * @param
         */
        fun getTestContent(projectId: Int, appId: Int): Collection<TestContent> {
            val response = createGet("$PARATRANZ_API/projects/$projectId/applications/$appId/tests")
                .execute()
            if (response.isOk) {
                return JacksonUtil.fromJson(response.body(), jacksonTypeRef<Collection<TestContent>>())
            }
            TODO("Todo....")
        }
    }

    private fun createGet(url: String, paramMap: Map<String, Any?> = mapOf()): HttpRequest {
        val httpRequest = HttpUtil.createGet(url)
        if (authorizationKey.isNotEmpty()) {
            httpRequest.auth(authorizationKey)
        }
        if (paramMap.isNotEmpty()) {
            httpRequest.form(paramMap)
        }
        return httpRequest
    }
}