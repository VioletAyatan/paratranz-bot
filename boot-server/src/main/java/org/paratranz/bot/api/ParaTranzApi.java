package org.paratranz.bot.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import com.google.gson.reflect.TypeToken;
import org.paratranz.bot.para.entity.Page;
import org.paratranz.bot.para.entity.PageResult;
import org.paratranz.bot.para.entity.data.Application;
import org.paratranz.bot.para.entity.data.Audit;
import org.paratranz.bot.tools.GsonUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParaTranzApi {
    private final String token;
    private static final String PARA_API_URL = "https://paratranz.cn/api";

    public ParaTranzApi(String token) {
        this.token = token;
    }

    private HttpRequest createRequest(String url, Method method) {
        return HttpRequest.of(url)
                .method(method)
                .header(Header.AUTHORIZATION, token);
    }

    private String doGet(String url, Map<String, Object> param) {
        return this.createRequest(url, Method.GET)
                .form(param)
                .execute()
                .body();
    }

    private String doGet(String url) {
        return this.doGet(url, null);
    }

    /**
     * 获取项目申请列表
     *
     * @param page      分页参数
     * @param projectId 项目id
     * @param status    状态（0待审核、1已拒绝、2已通过）
     */
    public PageResult<Application> listApplications(Page page, String projectId, Integer status) {
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
    public PageResult<Application> listApplications(String projectId) {
        return this.listApplications(null, projectId, null);
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
