package com.para.tranzai.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "tranzai")
public class TranzaiProperties {
    /**
     * paratranz项目id
     */
    private String projectId;
    /**
     * mirai机器人相关配置
     */
    private MiraiBotConfig miraiBotConfig;

    @Getter
    @Setter
    public static class MiraiBotConfig {
        /**
         * 机器人qq号
         */
        private Long qq;
        /**
         * 机器人qq密码
         */
        private String password;
        /**
         * 配置机器人默认转发消息到哪些群组.
         */
        private Long[] groups;

        public Long getqq() {
            return qq;
        }
    }
}
