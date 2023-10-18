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
import org.paratranz.bot.tools.GsonUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        public PageResult<Application> listApply(@NotNull Page page, @NotNull Integer projectId, Integer status) {
            Map<String, Object> params = BeanUtil.beanToMap(page, false, true);
            params.put("status", status);
            try (HttpResponse response = this.doGet(PARA_API_URL + "/projects/" + projectId + "/applications", params)) {
                if (response.isOk()) {
                    return GsonUtil.fromJson(response.body(), new TypeToken<>() {
                    });
                } else {
                    return new PageResult<>();
                }
            }
        }

        /**
         * 获取项目申请列表
         *
         * @param projectId 项目id
         */
        public PageResult<Application> listApply(@NotNull Integer projectId) {
            return this.listApply(Page.of(), projectId, null);
        }

        /**
         * 获取申请人测试内容
         *
         * @param applyId   申请项id
         * @param projectId 项目id
         */
        public List<Audit> getTestContent(@NotNull Integer applyId, @NotNull Integer projectId) {
            try (HttpResponse response = this.doGet(PARA_API_URL + "/projects/" + projectId + "/applications/" + applyId + "/tests")) {
                if (response.isOk()) {
                    return GsonUtil.fromJson(response.body(), new TypeToken<>() {
                    });
                } else {
                    return Collections.emptyList();
                }
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
                if (response.isOk()) {
                    return GsonUtil.fromJson(response.body(), new TypeToken<>() {
                    });
                } else {
                    throw new HttpException("请求出现错误：{}", response.body());
                }
            }
        }

        /**
         * 创建词条
         */
        public void createStrings() {

        }
    }
}
