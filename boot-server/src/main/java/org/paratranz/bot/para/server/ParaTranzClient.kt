package org.paratranz.bot.para.server

import cn.hutool.http.Header
import cn.hutool.http.HttpRequest
import com.google.gson.reflect.TypeToken
import org.paratranz.bot.para.entity.PageResult
import org.paratranz.bot.para.entity.data.Project
import org.paratranz.bot.para.entity.data.ProjectOverview
import org.paratranz.bot.tools.GsonUtil

/**
 * Paratranz-Api客户端.
 */
class ParaTranzClient(private val TOKEN: String) {

    companion object {
        private const val BASE_URL = "https://paratranz.cn/api"
    }


    /**
     * 获取项目列表
     */
    @JvmOverloads
    fun getProjects(page: Int = 1, pageSize: Int = 50): PageResult<Project>? {
        return GsonUtil.fromJson(get("$BASE_URL/projects"), object : TypeToken<PageResult<Project>>() {});
    }

    /**
     * 通过ID获取项目信息
     */
    fun getProjectOverrview(projectId: String): ProjectOverview? {
        return GsonUtil.fromJson(get(("$BASE_URL/project/$projectId")), ProjectOverview::class.java)
    }

    /**
     * todo 创建项目
     */
    fun createProject() {

    }

    /**
     * todo 更新项目
     */
    fun updateProject() {

    }

    /**
     * todo 删除项目
     */
    fun deleteProject() {

    }


    private fun get(url: String): String? {
        return HttpRequest.get(url)
            .header(Header.AUTHORIZATION, TOKEN)
            .execute().body()
    }

    private fun post(url: String): String? {
        return HttpRequest.post(url)
            .header(Header.AUTHORIZATION, TOKEN)
            .execute().body()
    }
}