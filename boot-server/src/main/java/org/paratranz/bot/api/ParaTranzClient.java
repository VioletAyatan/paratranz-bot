package org.paratranz.bot.api;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import com.google.gson.reflect.TypeToken;
import org.paratranz.bot.para.entity.PageResult;
import org.paratranz.bot.para.entity.data.Project;
import org.paratranz.bot.para.entity.data.ProjectOverview;
import org.paratranz.bot.tools.GsonUtil;

public class ParaTranzClient {
    private static final String BASE_URL = "https://paratranz.cn/api";
    private final String token;

    public ParaTranzClient(String token) {
        this.token = token;
    }

    private String doGet(String url) {
        return HttpRequest.of(url)
                .method(Method.GET)
                .header(Header.AUTHORIZATION, token) //todo
                .execute()
                .body();
    }

    public PageResult<Project> getProjects(int page, int pageSize) {
        return GsonUtil.fromJson(doGet(BASE_URL + "/projects"), new TypeToken<>() {
        });
    }

    public ProjectOverview getProjectOverrview(String projectId) {
        return GsonUtil.fromJson(doGet("$BASE_URL/project/$projectId"), ProjectOverview.class);
    }
}
