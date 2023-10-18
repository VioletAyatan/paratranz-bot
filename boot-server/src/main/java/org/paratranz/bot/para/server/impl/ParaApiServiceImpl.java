package org.paratranz.bot.para.server.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.ManagedBean;
import org.paratranz.bot.api.entity.IssuePageResult;
import org.paratranz.bot.api.entity.Page;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.data.*;
import org.paratranz.bot.para.constant.GameEnum;
import org.paratranz.bot.para.server.ParaApiService;
import org.paratranz.bot.tools.GsonUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ParaApiServiceImpl implements ParaApiService {

    private static final String PARA_API_URL = "https://paratranz.cn/api";

    private static final String PROJECT_API = PARA_API_URL + "/projects";

    private static final String HISTORY_API = PARA_API_URL + "/history";

    private static final String ISSUE_API = PARA_API_URL + "/issues";

    @Override
    public PageResult<Project> listProjects(GameEnum gameEnum) {
        return this.listProjects(null, gameEnum);
    }

    @Override
    public PageResult<Project> listProjects(Page page, GameEnum gameEnum) {
        String s = HttpUtil.get(PROJECT_API, BeanUtil.beanToMap(page, false, true));
        if (GsonUtil.isJsonStr(s)) {
            return GsonUtil.fromJson(s, new TypeToken<>() {
            });
        }
        return new PageResult<>();
    }

    @Override
    public ProjectOverview getProjectIntroduction(String projectId) {
        return GsonUtil.fromJson(HttpUtil.get(PROJECT_API + "/" + projectId), ProjectOverview.class);
    }

    @Override
    public PageResult<History> listProjectHistories(String projectId) {
        return this.listProjectHistories(null, projectId);
    }

    @Override
    public PageResult<History> listProjectHistories(Page page, String projectId) {
        Map<String, Object> params;
        if (page != null) {
            params = BeanUtil.beanToMap(page, false, true);
        } else {
            params = new HashMap<>();
        }
        params.put("project", projectId);
        String s = HttpUtil.get(HISTORY_API, params);
        if (GsonUtil.isJsonStr(s)) {
            return GsonUtil.fromJson(s, new TypeToken<>() {
            });
        }
        return new PageResult<>();
    }

    @Override
    public PageResult<Announcement> listAnnouncements(String projectId) {
        return this.listAnnouncements(null, projectId);
    }

    @Override
    public PageResult<Announcement> listAnnouncements(Page page, String projectId) {
        String s = HttpUtil.get(PROJECT_API + "/" + projectId + "/announcements",
                BeanUtil.beanToMap(page, false, true));
        if (GsonUtil.isJsonStr(s)) {
            return GsonUtil.fromJson(s, new TypeToken<>() {
            });
        }
        return new PageResult<>();
    }

    @Override
    public PageResult<Task> listTasks(Page page, String projectId) {
        String str = HttpUtil.get(PROJECT_API + "/" + projectId + "/tasks", BeanUtil.beanToMap(page, false, true));
        if (GsonUtil.isJsonStr(str)) {
            return GsonUtil.fromJson(str, new TypeToken<>() {
            });
        }
        return new PageResult<>();
    }

    @Override
    public PageResult<Task> listTasks(String projectId) {
        return this.listTasks(null, projectId);
    }

    @Override
    public List<File> listFiles(String projectId) {
        String str = HttpUtil.get(PROJECT_API + "/" + projectId + "/files");
        if (GsonUtil.isJsonStr(str)) {
            return GsonUtil.fromJson(str, new TypeToken<>() {
            });
        }
        return Collections.emptyList();
    }

    @Override
    public PageResult<Terms> listTerms(Page page, String projectId) {
        String str = HttpUtil.get(PROJECT_API + "/" + projectId + "/terms", BeanUtil.beanToMap(page, false, true));
        if (GsonUtil.isJsonStr(str)) {
            return GsonUtil.fromJson(str, new TypeToken<>() {
            });
        }
        return new PageResult<>();
    }

    @Override
    public PageResult<Terms> listTerms(String projectId) {
        return this.listTerms(null, projectId);
    }

    @Override
    public IssuePageResult<Issue> listIssues(String projectId, int status) {
        return this.listIssues(null, projectId, status);
    }

    @Override
    public IssuePageResult<Issue> listIssues(Page page, String projectId, int status) {
        Map<String, Object> params;
        if (page != null) {
            params = BeanUtil.beanToMap(page, false, true);
        } else {
            params = new HashMap<>();
        }
        params.put("project", projectId);
        params.put("status", status);

        String str = HttpUtil.get(ISSUE_API, params);
        if (GsonUtil.isJsonStr(str)) {
            return GsonUtil.fromJson(str, new TypeToken<>() {
            });
        }
        return new IssuePageResult<>();
    }

    @Override
    public List<Member> getMembers(String projectId) {
        String s = HttpUtil.get(PROJECT_API + "/" + projectId + "/members");
        if (GsonUtil.isJsonStr(s)) {
            return GsonUtil.fromJson(s, new TypeToken<>() {
            });
        }
        return Collections.emptyList();
    }

    @Override
    public PageResult<Application> listApplications(Page page, String projectId, Integer status) {
        Map<String, Object> params = BeanUtil.beanToMap(Page.of(), false, true);
        params.put("status", status);
        String s = HttpUtil.get(PROJECT_API + "/" + projectId + "/applications", params);
        if (GsonUtil.isJsonStr(s)) {
            return GsonUtil.fromJson(s, new TypeToken<>() {
            });
        }
        return new PageResult<>();
    }

    @Override
    public PageResult<Application> listApplications(String projectId) {
        return this.listApplications(null, projectId, null);
    }

    @Override
    public List<Audit> getTestContent(int applicationId, String projectId) {
        String s = HttpUtil.get(PROJECT_API + "/" + projectId + "/applications/" + applicationId + "/tests");
        if (GsonUtil.isJsonStr(s)) {
            return GsonUtil.fromJson(s, new TypeToken<>() {
            });
        }
        return Collections.emptyList();
    }
}
