package com.para.tranzai.para.server;

import com.para.tranzai.para.constant.GameEnum;
import com.para.tranzai.para.entity.IssuePageResult;
import com.para.tranzai.para.entity.Page;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.*;

import java.util.List;

/**
 * 针对调用ParaTranz各种公开的Web-Api接口做一个简单封装.
 * @author Ankol
 */
public interface ParaApiService {

    /**
     * 按游戏列举para项目列表
     * @param gameEnum 游戏类型，为空则包含所有游戏类型.
     */
    PageResult<Project> listProjects(GameEnum gameEnum);

    /**
     * 按游戏列举para项目列表
     * @param page 分页参数
     * @param gameEnum 游戏类型，为空则包含所有游戏类型.
     */
    PageResult<Project> listProjects(Page page, GameEnum gameEnum);

    /**
     * 获取项目概述
     * @param projectId 项目id
     */
    ProjectOverview getProjectIntroduction(String projectId);

    /**
     * 获取项目历史记录列表
     * @param projectId 项目id
     */
    PageResult<History> listProjectHistories(String projectId);

    /**
     * 获取项目历史记录列表
     * @param projectId 项目id
     * @param page 分页参数
     */
    PageResult<History> listProjectHistories(Page page, String projectId);

    /**
     * 获取项目公告列表
     * @param projectId 项目id
     */
    PageResult<Announcement> listAnnouncements(String projectId);

    /**
     * 获取项目公告列表
     * @param projectId 项目id
     * @param page 分页参数
     */
    PageResult<Announcement> listAnnouncements(Page page, String projectId);

    /**
     * 获取项目的任务列表
     * @param page 分页参数
     * @param projectId 项目id
     */
    PageResult<Task> listTasks(Page page, String projectId);

    /**
     * 获取项目的任务列表
     * @param projectId 项目id
     */
    PageResult<Task> listTasks(String projectId);

    /**
     * 获取项目文件列表
     * @param projectId 项目id
     */
    List<File> listFiles(String projectId);

    /**
     * 获取项目术语列表
     * @param page 分页参数
     * @param projectId 项目id
     */
    PageResult<Terms> listTerms(Page page, String projectId);

    /**
     * 获取项目术语列表
     * @param projectId 项目id
     */
    PageResult<Terms> listTerms(String projectId);

    /**
     * 获取项目讨论列表
     * @param projectId 项目id
     * @param status 状态（0为讨论中，1为已关闭）
     */
    IssuePageResult<Issue> listIssues(String projectId, int status);

    /**
     * 获取项目讨论列表
     * @param projectId 项目id
     * @param status 状态（0为讨论中，1为已关闭）
     */
    IssuePageResult<Issue> listIssues(Page page, String projectId, int status);

    /**
     * 获取项目成员列表
     * @param projectId 项目id
     */
    List<Member> getMembers(String projectId);

    /**
     * 获取项目申请列表
     * @param page 分页参数
     * @param projectId 项目id
     * @param status 状态（0待审核、1已拒绝、2已通过）
     */
    PageResult<Application> listApplications(Page page, String projectId, Integer status);

    /**
     * 获取项目申请列表
     * @param projectId 项目id
     */
    PageResult<Application> listApplications(String projectId);

    /**
     * 查看申请人翻译
     * @param applicationId 申请项id
     * @param projectId 项目id
     */
    List<Audit> getTestContent(int applicationId, String projectId);
}
