package com.para.tranzai.para.server.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.reflect.TypeToken;
import com.para.tranzai.constant.GameEnum;
import com.para.tranzai.para.entity.*;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.tools.GsonUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@SuppressWarnings("unchecked")
public class ParaServiceImpl implements ParaService {

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
        return (PageResult<Project>) GsonUtils.fromJson(HttpUtil.get(PROJECT_API, BeanUtil.beanToMap(page, false, true)),
                new TypeToken<PageResult<Project>>() {
                }.getType());
    }

    @Override
    public ProjectOverview getProjectOverview(String projectId) {
        return GsonUtils.fromJson(HttpUtil.get(PROJECT_API + "/" + projectId), ProjectOverview.class);
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
        return (PageResult<History>) GsonUtils.fromJson(HttpUtil.get(HISTORY_API, params),
                new TypeToken<PageResult<History>>() {
                }.getType());
    }

    @Override
    public PageResult<Announcement> listAnnouncements(String projectId) {
        return this.listAnnouncements(null, projectId);
    }

    @Override
    public PageResult<Announcement> listAnnouncements(Page page, String projectId) {
        return (PageResult<Announcement>) GsonUtils.fromJson(HttpUtil.get(PROJECT_API + "/" + projectId + "/announcements",
                        BeanUtil.beanToMap(page, false, true)),
                new TypeToken<PageResult<Announcement>>() {
                }.getType());
    }

    @Override
    public PageResult<Task> listTasks(Page page, String projectId) {
        return (PageResult<Task>) GsonUtils.fromJson(
                HttpUtil.get(PROJECT_API + "/" + projectId + "/tasks", BeanUtil.beanToMap(page, false, true)
                ), new TypeToken<PageResult<Task>>() {
                }.getType());
    }

    @Override
    public PageResult<Task> listTasks(String projectId) {
        return this.listTasks(null, projectId);
    }

    @Override
    public List<File> listFiles(String projectId) {
        return (List<File>) GsonUtils.fromJson(HttpUtil.get(PROJECT_API + "/" + projectId + "/files")
                , new TypeToken<List<File>>() {
                }.getType());
    }

    @Override
    public PageResult<Terms> listTerms(Page page, String projectId) {
        return (PageResult<Terms>) GsonUtils.fromJson(
                HttpUtil.get(PROJECT_API + "/" + projectId + "/terms", BeanUtil.beanToMap(page, false, true)),
                new TypeToken<PageResult<Terms>>() {
                }.getType());
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
        return (IssuePageResult<Issue>) GsonUtils.fromJson(HttpUtil.get(ISSUE_API, params),
                new TypeToken<IssuePageResult<Issue>>() {
                }.getType());
    }

    @Override
    public List<Member> getMembers(String projectId) {
        return (List<Member>) GsonUtils.fromJson(HttpUtil.get(PROJECT_API + "/" + projectId + "/members"),
                new TypeToken<List<Member>>() {
                }.getType());
    }

    @Override
    public PageResult<Application> listApplications(Page page, String projectId, Integer status) {
        Map<String, Object> params = BeanUtil.beanToMap(Optional.ofNullable(page).orElse(new Page()), false, true);
        Optional.ofNullable(status).ifPresent(value -> params.put("status", value));
        return (PageResult<Application>) GsonUtils.fromJson(
                HttpUtil.get(PROJECT_API + "/" + projectId + "/applications", params),
                new TypeToken<PageResult<Application>>() {
                }.getType());
    }

    @Override
    public PageResult<Application> listApplications(String projectId) {
        return this.listApplications(null, projectId, null);
    }
}
