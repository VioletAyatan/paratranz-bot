package com.para.tranzai.mirai;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotConfig {
    /**
     * 机器人qq号
     */
    private Long qqNumber;
    /**
     * 机器人qq密码
     */
    private String password;
    /**
     * 配置机器人默认转发消息到哪些群组.
     */
    private Long[] groups = new Long[0];
}
