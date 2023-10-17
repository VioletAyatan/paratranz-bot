package org.paratranz.bot.api;

import cn.hutool.core.bean.BeanUtil;
import com.google.gson.reflect.TypeToken;
import org.paratranz.bot.api.entity.Page;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.data.Application;
import org.paratranz.bot.api.entity.data.Audit;
import org.paratranz.bot.tools.GsonUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParaTranzApi extends AbstractApi {
    private static final String PARA_API_URL = "https://paratranz.cn/api";

    public ParaTranzApi(String authorization) {
        super(authorization);
    }

    public final Apply apply = new Apply(authorization);


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
        public PageResult<Application> listApply(Page page, String projectId, Integer status) {
            Map<String, Object> params = BeanUtil.beanToMap(Optional.ofNullable(page).orElse(new Page()), false, true);
            params.put("status", status);
            String json = this.doGet(PARA_API_URL + "/projects/" + projectId + "/applications", params);
            if (GsonUtil.isJsonStr(json)) {
                return GsonUtil.fromJson(json, new TypeToken<>() {
                });
            }
            return new PageResult<>();
        }

        /**
         * 获取项目申请列表
         *
         * @param projectId 项目id
         */
        public PageResult<Application> listApply(String projectId) {
            return this.listApply(null, projectId, null);
        }

        /**
         * 查看申请人翻译
         *
         * @param applicationId 申请项id
         * @param projectId     项目id
         */
        public List<Audit> getTestContent(Integer applicationId, String projectId) {
            String json = this.doGet(PARA_API_URL + "/projects/" + projectId + "/applications/" + applicationId + "/tests");
            if (GsonUtil.isJsonStr(json)) {
                return GsonUtil.fromJson(json, new TypeToken<>() {
                });
            }
            return Collections.emptyList();
        }
    }
}
