package com.para.tranzai.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "system")
public class SystemProperties {
    /**
     * paratranz项目id
     */
    private String projectId;
    /**
     * mirai机器人相关配置
     */
    private BotConfig botConfig;
    /**
     * 模块控制，调试用，用以控制系统各模块激活
     */
    private ModuleControl moduleControl;

    @Getter
    @Setter
    public static class ModuleControl {
        /**
         * 影响系统中有关paraTranz相关服务的启停
         */
        private boolean paraActive;
        /**
         * 影响系统中有关mirai相关服务的启停
         */
        private boolean miraiActive;
    }

    @Getter
    @Setter
    public static class BotConfig {
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
}
