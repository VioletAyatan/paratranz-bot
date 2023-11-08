package org.paratranz.bot.api.entity.data;

import lombok.Data;

/**
 * 更新用户信息所使用的配置
 * @author Administrator
 */
@Data
public class UserConfig {
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 个人介绍
     */
    private String bio;
    /**
     * 头像Url
     */
    private String avatar;
}
