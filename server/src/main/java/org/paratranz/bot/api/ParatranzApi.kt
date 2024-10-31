package org.paratranz.bot.api

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.io.StreamProgress
import cn.hutool.core.lang.Assert
import cn.hutool.http.HttpException
import cn.hutool.http.HttpStatus
import cn.hutool.http.HttpUtil
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.paratranz.bot.api.constant.ApplyStatus
import org.paratranz.bot.api.entity.Page
import org.paratranz.bot.api.entity.PageResult
import org.paratranz.bot.api.entity.artifact.ArtifactResult
import org.paratranz.bot.api.entity.artifact.TriggerExportRes
import org.paratranz.bot.api.entity.common.PrUser
import org.paratranz.bot.api.entity.data.*
import org.paratranz.bot.api.entity.terms.TermConfig
import org.paratranz.bot.api.entity.terms.TermConfigRes
import org.paratranz.bot.api.entity.terms.TermDetail
import java.io.File
import java.util.*
import java.util.regex.Pattern

/**
 * 基于[Paratranz](https://paratranz.cn/)API文档实现的简单客户端
 *
 * @author Administrator
 * @see [https://paratranz.cn/docs](https://paratranz.cn/docs)
 */
class ParatranzApi(authorization: String) : AbstractApi(authorization) {
    companion object {
        private const val PARA_API_URL = "https://paratranz.cn/api"
    }

    /**
     * 项目申请
     */
    @JvmField
    val apply = Apply(authorization)

    /**
     * 词条
     */
    @JvmField
    val strings = Strings(authorization)

    /**
     * 术语相关接口
     */
    @JvmField
    val terms = Terms(authorization)

    /**
     * 导出压缩包及下载相关接口
     */
    @JvmField
    val artifacts = Artifacts(authorization)

    /**
     * 成员贡献相关接口
     */
    @JvmField
    val scores = Scores(authorization)

    /**
     * 用户相关接口
     */
    @JvmField
    val users = Users(authorization)

    /**
     * 项目相关接口
     */
    @JvmField
    val projects = Projects(authorization)

    /**
     * 项目相关接口
     *
     * @author Administrator
     */
    class Projects(authorization: String) : AbstractApi(authorization) {
        /**
         * 获取项目列表
         *
         * @param page 分页参数
         * @return [PageResult]
         */
        @JvmOverloads
        fun listProjects(page: Page? = Page.of()): PageResult<Project> {
            val param = BeanUtil.beanToMap(page, false, false)
            this.doGet("$PARA_API_URL/projects", param).use { response ->
                return this.processResponse(
                    response,
                    jacksonTypeRef<PageResult<Project>>()
                )
            }
        }
    }

    /**
     * 项目申请
     *
     * @author Administrator
     */
    class Apply(authorization: String) : AbstractApi(authorization) {
        /**
         * 获取项目申请列表
         *
         * @param projectId 项目id
         * @param page      分页参数
         * @param status    状态（0待审核、1已拒绝、2已通过）
         */
        @JvmOverloads
        fun listApply(
            projectId: Int,
            page: Page? = Page.of(),
            status: ApplyStatus? = null
        ): PageResult<Application> {
            val params = BeanUtil.beanToMap(page, false, true)
            status?.let { params["status"] = it.code }
            super.doGet("$PARA_API_URL/projects/$projectId/applications", params).use { response ->
                return processResponse(
                    response,
                    jacksonTypeRef<PageResult<Application>>()
                )
            }
        }

        /**
         * 获取申请人测试内容
         *
         * @param applyId   申请项id
         * @param projectId 项目id
         */
        fun getTestContent(applyId: Int, projectId: Int): List<Audit> {
            super.doGet("$PARA_API_URL/projects/$projectId/applications/$applyId/tests")
                .use { response ->
                    return processResponse(
                        response,
                        jacksonTypeRef<List<Audit>>()
                    )
                }
        }
    }

    /**
     * 词条
     *
     * @author Administrator
     */
    class Strings  //todo...
        (authorization: String) : AbstractApi(authorization) {
        /**
         * 获取项目词条
         *
         * @param projectId 项目ID
         * @return [PageResult]
         */
        fun getStrings(projectId: String): PageResult<GetStringRes> {
            super.doGet("$PARA_API_URL/projects/$projectId/strings").use { response ->
                return processResponse(
                    response,
                    jacksonTypeRef<PageResult<GetStringRes>>()
                )
            }
        }

        /**
         * 创建词条 todo
         */
        fun createStrings() {}
    }

    /**
     * 术语
     *
     * @author Administrator
     */
    class Terms(authorization: String) : AbstractApi(authorization) {
        /**
         * 获取项目术语列表
         *
         * @param projectId 项目Id
         * @param page      分页对象
         * @return [PageResult]
         * @throws HttpException Api调用出错抛出
         */
        @JvmOverloads
        @Throws(HttpException::class)
        fun listTerms(projectId: Int, page: Page? = Page.of()): PageResult<TermDetail> {
            val params = BeanUtil.beanToMap(page, false, true)
            super.doGet("$PARA_API_URL/projects/$projectId/terms", params).use { response ->
                return processResponse(
                    response,
                    jacksonTypeRef<PageResult<TermDetail>>()
                )
            }
        }

        /**
         * 通过ID获取项目术语信息
         *
         * @param projectId 项目ID
         * @param termId    术语ID
         * @return [TermDetail]
         * @throws HttpException Api调用出错抛出
         */
        @Throws(HttpException::class)
        fun getTermDetail(projectId: Int, termId: Int): TermConfigRes {
            super.doGet("$PARA_API_URL/projects/$projectId/terms/$termId")
                .use { response -> return processResponse(response, TermConfigRes::class.java) }
        }

        /**
         * 创建新术语，如果已存在相同术语会失败
         *
         * @param projectId 项目Id
         * @param config    术语配置
         * @return [TermConfigRes]
         * @throws HttpException Api调用出错抛出
         */
        @Throws(HttpException::class)
        fun createTerm(projectId: Int, config: TermConfig): TermConfigRes {
            super.doPost("$PARA_API_URL/projects/$projectId/terms", config)
                .use { response -> return processResponse(response, TermConfigRes::class.java) }
        }

        /**
         * 修改术语
         *
         * @param projectId 项目ID
         * @param termId    术语ID
         * @param config    术语配置
         * @return [TermConfig]
         * @throws HttpException Api调用出错抛出
         */
        @Throws(HttpException::class)
        fun editTerm(projectId: Int, termId: Int, config: TermConfig): TermConfigRes {
            super.doPut("$PARA_API_URL/projects/$projectId/terms/$termId", config)
                .use { response -> return processResponse(response, TermConfigRes::class.java) }
        }

        /**
         * 通过ID删除术语，仅创建者及管理员可以删除
         *
         * @param projectId 项目ID
         * @param termId    术语ID
         * @return [Boolean]
         */
        fun deleteTerm(projectId: Int, termId: Int): Boolean {
            super.doDelete("$PARA_API_URL/projects/$projectId/terms/$termId")
                .use { response -> return response.isOk }
        }
    }

    /**
     * 导出压缩包及下载相关接口
     *
     * @author Administrator
     */
    class Artifacts(authorization: String) : AbstractApi(authorization) {
        /**
         * 获取最近一次导出的结果
         *
         * @param projectId 项目ID
         * @return [ArtifactResult]
         * @throws HttpException Api调用出错抛出
         */
        @Throws(HttpException::class)
        fun getExportResult(projectId: Int): ArtifactResult {
            super.doGet("$PARA_API_URL/projects/$projectId/artifacts")
                .use { response -> return processResponse(response, ArtifactResult::class.java) }
        }

        /**
         * 手动触发导出操作，仅管理员可使用
         *
         * @param projectId 项目ID
         * @return [TriggerExportRes]
         * @throws HttpException Api调用出错抛出
         */
        @Throws(HttpException::class)
        fun triggerExport(projectId: Int): TriggerExportRes {
            super.doPost("$PARA_API_URL/projects/$projectId/artifacts")
                .use { response -> return processResponse(response, TriggerExportRes::class.java) }
        }

        /**
         * 下载导出的压缩包
         *
         * @param projectId 项目ID
         * @param path      目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
         * @return 下载的文件大小（单位Byte）
         */
        fun downloadArtifacts(projectId: Int, path: String): Long {
            return this.downloadArtifacts(projectId, File(path))
        }

        /**
         * 下载导出的压缩包
         *
         * @param projectId 项目ID
         * @param file      目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
         * @param progress  进度条回调接口
         * @return 下载的文件大小（单位Byte）
         */
        @JvmOverloads
        fun downloadArtifacts(projectId: Int, file: File, progress: StreamProgress? = null): Long {
            Assert.notNull(file, "文件路径不能为空")
            super.doGet("$PARA_API_URL/projects/$projectId/artifacts/download").use { response ->
                return if (response.status == HttpStatus.HTTP_MOVED_TEMP) {
                    val downloadUrl = extractDownloadUrl(response.body())
                    HttpUtil.downloadFile(downloadUrl, file, progress)
                } else {
                    throw HttpException(response.body())
                }
            }
        }

        private fun extractDownloadUrl(body: String): String {
            val regex = Pattern.compile("<a\\s+[^>]*href=\"([^\"]+)\"")
            val matcher = regex.matcher(body)
            return if (matcher.find()) {
                matcher.group(1)
            } else {
                throw HttpException("Can't found artifacts download url!")
            }
        }
    }

    /**
     * 成员贡献相关接口
     *
     * @author Administrator
     */
    class Scores(authorization: String) : AbstractApi(authorization) {
        /**
         * 查看项目所有的贡献
         *
         * @param projectId 项目ID
         * @param page      分页
         * @param uid       指定用户ID
         * @param operation 指定类型获取贡献（可用值：translate、edit、review）
         * @param start     筛选开始时间
         * @param end       筛选结束时间
         * @return [PageResult]
         * @throws HttpException Api调用出错抛出
         */
        @JvmOverloads
        @Throws(HttpException::class)
        fun getProjectScores(
            projectId: Int,
            page: Page? = Page.of(),
            uid: Int? = null,
            operation: String? = null,
            start: String? = null,
            end: String? = null
        ): PageResult<PrScore> {
            val param = BeanUtil.beanToMap(page, false, true)
            page?.let { param["page"] = it }
            uid?.let { param["uid"] = it }
            operation?.let { param["operation"] = it }
            start?.let { param["start"] = it }
            end?.let { param["end"] = it }
            super.doGet("$PARA_API_URL/projects/$projectId/scores").use { response ->
                return processResponse(
                    response,
                    jacksonTypeRef<PageResult<PrScore>>()
                )
            }
        }
    }

    /**
     * 用户相关接口
     *
     * @author Administrator
     */
    class Users(authorization: String) : AbstractApi(authorization) {
        /**
         * 获取用户信息
         *
         * @param uid 用户id
         * @return [PrUser] 用户信息
         */
        fun getUserDetail(uid: Int): PrUser {
            this.doGet("$PARA_API_URL/users/$uid")
                .use { response -> return processResponse(response, PrUser::class.java) }
        }

        /**
         * 更改用户信息（仅支持修改自己的信息）
         * **注意，这里测试这个API的时候，配置参数如果只传某一条条件的话返回会报错，但是实际上数据修改成功了，这是paratranz网站api的bug**
         *
         * @return [PrUser] 用户信息
         */
        fun updateUserInfo(uid: Int, config: UserConfig?): PrUser {
            doPut("$PARA_API_URL/users/$uid", config).use { response ->
                return processResponse(
                    response,
                    PrUser::class.java
                )
            }
        }
    }

}
