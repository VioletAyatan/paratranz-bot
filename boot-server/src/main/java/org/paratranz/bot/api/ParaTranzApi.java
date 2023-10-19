package org.paratranz.bot.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpResponse;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.paratranz.bot.api.entity.Page;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.data.Application;
import org.paratranz.bot.api.entity.data.Audit;
import org.paratranz.bot.api.entity.data.GetStringRes;
import org.paratranz.bot.api.entity.terms.TermConfig;
import org.paratranz.bot.api.entity.terms.TermConfigRes;
import org.paratranz.bot.api.entity.terms.TermDetail;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 基于<a href="https://paratranz.cn/">Paratranz</a>API文档实现的简单客户端
 *
 * @author Administrator
 * @see <a href="https://paratranz.cn/docs">https://paratranz.cn/docs</a>
 */
public class ParaTranzApi extends AbstractApi {
    private static final String PARA_API_URL = "https://paratranz.cn/api";

    public ParaTranzApi(String authorization) {
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
     * 术语
     */
    public final Terms terms = new Terms(authorization);

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
            try (HttpResponse response = this.doGet(PARA_API_URL + "/projects/" + projectId + "/applications", params)) {
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
            try (HttpResponse response = this.doGet(PARA_API_URL + "/projects/" + projectId + "/applications/" + applyId + "/tests")) {
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
            try (HttpResponse response = this.doGet(PARA_API_URL + "/projects/" + projectId + "/strings")) {
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
            try (HttpResponse response = doGet(PARA_API_URL + "/projects/" + projectId + "/terms", params)) {
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
            try (HttpResponse response = doGet(PARA_API_URL + "/projects/" + projectId + "/terms/" + termId)) {
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
            try (HttpResponse response = doPost(PARA_API_URL + "/projects/" + projectId + "/terms", config)) {
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
            try (HttpResponse response = doPut(PARA_API_URL + "/projects/" + projectId + "/terms/" + termId, config)) {
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
            try (HttpResponse response = doDelete(PARA_API_URL + "/projects/" + projectId + "/terms/" + termId)) {
                return response.isOk();
            }
        }
    }
}
