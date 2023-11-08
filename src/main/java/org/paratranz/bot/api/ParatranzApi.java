package org.paratranz.bot.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.paratranz.bot.api.entity.Page;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.artifact.ArtifactResult;
import org.paratranz.bot.api.entity.artifact.TriggerExportRes;
import org.paratranz.bot.api.entity.data.Application;
import org.paratranz.bot.api.entity.data.Audit;
import org.paratranz.bot.api.entity.data.GetStringRes;
import org.paratranz.bot.api.entity.data.PrScore;
import org.paratranz.bot.api.entity.terms.TermConfig;
import org.paratranz.bot.api.entity.terms.TermConfigRes;
import org.paratranz.bot.api.entity.terms.TermDetail;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基于<a href="https://paratranz.cn/">Paratranz</a>API文档实现的简单客户端
 *
 * @author Administrator
 * @see <a href="https://paratranz.cn/docs">https://paratranz.cn/docs</a>
 */
public class ParatranzApi extends AbstractApi {
    private static final String PARA_API_URL = "https://paratranz.cn/api";

    public ParatranzApi(String authorization) {
        super(authorization);
    }

    /**
     * 项目申请
     */
    public final Apply apply = new Apply(authorization);
    /**
     * 词条
     */
    public final Strings strings = new Strings(authorization);
    /**
     * 术语相关接口
     */
    public final Terms terms = new Terms(authorization);
    /**
     * 导出压缩包及下载相关接口
     */
    public final Artifacts artifacts = new Artifacts(authorization);
    /**
     * 成员贡献相关接口
     */
    public final Scores scores = new Scores(authorization);

    /**
     * 项目申请
     *
     * @author Administrator
     */
    public static class Apply extends AbstractApi {
        protected Apply(String authorization) {
            super(authorization);
        }

        /**
         * 获取项目申请列表
         *
         * @param page      分页参数
         * @param projectId 项目id
         * @param status    状态（0待审核、1已拒绝、2已通过）
         */
        public PageResult<Application> listApply(@NotNull Page page, int projectId, Integer status) {
            Map<String, Object> params = BeanUtil.beanToMap(page, false, true);
            params.put("status", status);
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/applications", params)) {
                return processResponse(response, new TypeToken<>() {
                });
            }
        }

        /**
         * 获取项目申请列表
         *
         * @param projectId 项目id
         */
        public PageResult<Application> listApply(int projectId) {
            return this.listApply(Page.of(), projectId, null);
        }

        /**
         * 获取申请人测试内容
         *
         * @param applyId   申请项id
         * @param projectId 项目id
         */
        public List<Audit> getTestContent(int applyId, int projectId) {
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/applications/" + applyId + "/tests")) {
                return processResponse(response, new TypeToken<>() {
                });
            }
        }
    }

    /**
     * 词条
     *
     * @author Administrator
     */
    public static class Strings extends AbstractApi {
        //todo...
        protected Strings(String authorization) {
            super(authorization);
        }

        /**
         * 获取项目词条
         *
         * @param projectId 项目ID
         * @return {@link PageResult<GetStringRes>}
         */
        public PageResult<GetStringRes> getStrings(String projectId) {
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/strings")) {
                return processResponse(response, new TypeToken<>() {
                });
            }
        }

        /**
         * 创建词条 todo
         */
        public void createStrings() {

        }
    }

    /**
     * 术语
     *
     * @author Administrator
     */
    public static class Terms extends AbstractApi {
        protected Terms(String authorization) {
            super(authorization);
        }

        /**
         * 获取项目术语列表
         *
         * @param projectId 项目Id
         * @param page      分页对象
         * @return {@link PageResult<TermDetail>}
         * @throws HttpException Api调用出错抛出
         */
        public PageResult<TermDetail> listTerms(int projectId, Page page) throws HttpException {
            Map<String, Object> params = BeanUtil.beanToMap(Optional.ofNullable(page).orElse(Page.of()), false, true);
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/terms", params)) {
                return processResponse(response, new TypeToken<>() {
                });
            }
        }

        /**
         * 获取项目术语列表
         *
         * @param projectId 项目Id
         * @return {@link PageResult<TermDetail>}
         * @throws HttpException Api调用出错抛出
         */
        public PageResult<TermDetail> listTerms(int projectId) throws HttpException {
            return this.listTerms(projectId, Page.of());
        }

        /**
         * 通过ID获取项目术语信息
         *
         * @param projectId 项目ID
         * @param termId    术语ID
         * @return {@link TermDetail}
         * @throws HttpException Api调用出错抛出
         */
        public TermConfigRes getTermDetail(int projectId, int termId) throws HttpException {
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/terms/" + termId)) {
                return processResponse(response, TermConfigRes.class);
            }
        }

        /**
         * 创建新术语，如果已存在相同术语会失败
         *
         * @param projectId 项目Id
         * @param config    术语配置
         * @return {@link TermConfigRes}
         * @throws HttpException Api调用出错抛出
         */
        public TermConfigRes createTerm(int projectId, TermConfig config) throws HttpException {
            try (HttpResponse response = super.doPost(PARA_API_URL + "/projects/" + projectId + "/terms", config)) {
                return processResponse(response, TermConfigRes.class);
            }
        }

        /**
         * 修改术语
         *
         * @param projectId 项目ID
         * @param termId    术语ID
         * @param config    术语配置
         * @return {@link TermConfig}
         * @throws HttpException Api调用出错抛出
         */
        public TermConfigRes editTerm(int projectId, int termId, TermConfig config) throws HttpException {
            try (HttpResponse response = super.doPut(PARA_API_URL + "/projects/" + projectId + "/terms/" + termId, config)) {
                return processResponse(response, TermConfigRes.class);
            }
        }

        /**
         * 通过ID删除术语，仅创建者及管理员可以删除
         *
         * @param projectId 项目ID
         * @param termId    术语ID
         * @return {@link Boolean}
         */
        public boolean deleteTerm(int projectId, int termId) {
            try (HttpResponse response = super.doDelete(PARA_API_URL + "/projects/" + projectId + "/terms/" + termId)) {
                return response.isOk();
            }
        }
    }

    /**
     * 导出压缩包及下载相关接口
     *
     * @author Administrator
     */
    public static class Artifacts extends AbstractApi {

        protected Artifacts(String authorization) {
            super(authorization);
        }

        /**
         * 获取最近一次导出的结果
         *
         * @param projectId 项目ID
         * @return {@link ArtifactResult}
         * @throws HttpException Api调用出错抛出
         */
        public ArtifactResult getExportResult(int projectId) throws HttpException {
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/artifacts")) {
                return processResponse(response, ArtifactResult.class);
            }
        }

        /**
         * 手动触发导出操作，仅管理员可使用
         *
         * @param projectId 项目ID
         * @return {@link TriggerExportRes}
         * @throws HttpException Api调用出错抛出
         */
        public TriggerExportRes triggerExport(int projectId) throws HttpException {
            try (HttpResponse response = super.doPost(PARA_API_URL + "/projects/" + projectId + "/artifacts")) {
                return processResponse(response, TriggerExportRes.class);
            }
        }

        /**
         * 下载导出的压缩包
         *
         * @param projectId 项目ID
         * @param path      目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
         * @return 下载的文件大小（单位Byte）
         */
        public long downloadArtifacts(int projectId, String path) {
            return this.downloadArtifacts(projectId, new File(path));
        }

        /**
         * 下载导出的压缩包
         *
         * @param projectId 项目ID
         * @param file      目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
         * @return 下载的文件大小（单位Byte）
         */
        public long downloadArtifacts(int projectId, File file) {
            return this.downloadArtifacts(projectId, file, null);
        }

        /**
         * 下载导出的压缩包
         *
         * @param projectId 项目ID
         * @param file      目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
         * @param progress  进度条回调接口
         * @return 下载的文件大小（单位Byte）
         */
        public long downloadArtifacts(int projectId, File file, StreamProgress progress) {
            Assert.notNull(file, "文件路径不能为空");
            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/artifacts/download")) {
                if (response.getStatus() == HttpStatus.HTTP_MOVED_TEMP) {
                    String downloadUrl = this.extractDownloadUrl(response.body());
                    return HttpUtil.downloadFile(downloadUrl, file, progress);
                } else {
                    throw new HttpException(response.body());
                }
            }
        }

        private String extractDownloadUrl(String body) {
            Pattern regex = Pattern.compile("<a\\s+[^>]*href=\"([^\"]+)\"");
            Matcher matcher = regex.matcher(body);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new HttpException("Can't found artifacts download url!");
            }
        }
    }

    /**
     * 成员贡献相关接口
     *
     * @author Administrator
     */
    public static class Scores extends AbstractApi {
        protected Scores(String authorization) {
            super(authorization);
        }

        /**
         * 查看项目所有的贡献
         *
         * @param projectId 项目ID
         * @param page      分页
         * @param uid       指定用户ID
         * @param operation 指定类型获取贡献（可用值：translate、edit、review）
         * @param start     筛选开始时间
         * @param end       筛选结束时间
         * @return {@link PageResult<Object>}
         * @throws HttpException Api调用出错抛出
         */
        public PageResult<PrScore> getProjectScores(int projectId, Page page, Integer uid, String operation, String start, String end) throws HttpException {
            Map<String, Object> param = BeanUtil.beanToMap(page, false, true);
            Optional.ofNullable(uid).ifPresent(val -> param.put("uid", val));
            Optional.ofNullable(operation).ifPresent(val -> param.put("operation", val));
            Optional.ofNullable(start).ifPresent(val -> param.put("start", val));
            Optional.ofNullable(end).ifPresent(val -> param.put("end", val));

            try (HttpResponse response = super.doGet(PARA_API_URL + "/projects/" + projectId + "/scores")) {
                return processResponse(response, new TypeToken<>() {
                });
            }
        }


        /**
         * 查看项目所有的贡献
         *
         * @param projectId 项目ID
         * @param page      分页
         * @return
         * @throws HttpException Api调用出错抛出
         */
        public PageResult<PrScore> getProjectScores(int projectId, Page page) throws HttpException {
            return this.getProjectScores(projectId, page, null, null, null, null);
        }

        /**
         * 查看项目所有的贡献
         *
         * @param projectId 项目ID
         * @return {@link PageResult<PrScore>}
         * @throws HttpException Api调用出错抛出
         */
        public PageResult<PrScore> getProjectScores(int projectId) throws HttpException {
            return this.getProjectScores(projectId, Page.of());
        }
    }
}
