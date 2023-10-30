package org.paratranz.bot.api.entity.common;

import cn.hutool.core.date.DateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 头像url
     */
    private String avatar;
    /**
     * 用户最近访问时间
     */
    private DateTime lastVisit;
    /**
     * 创建时间
     */
    private DateTime createdAt;
    /**
     * 更新时间
     */
    private DateTime updatedAt;
    /**
     * 用户简介
     */
    private String bio;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户信用值，低于0将会被限制使用
     */
    private Integer credit;
    /**
     * 用户翻译的词条数量
     */
    private Integer translated;
    /**
     * 用户编辑的词条数量
     */
    private Integer edited;
    /**
     * 用户审核的词条数量
     */
    private Integer reviewed;
    /**
     * 用户发布的评论数
     */
    private Integer commented;
    /**
     * 用户的贡献值（PP），计算公式为 1 * 翻译词条总词数 + 0.5 * 编辑词条总词数 + 0.2 * 审核词条总词数
     */
    private Number points;
}
