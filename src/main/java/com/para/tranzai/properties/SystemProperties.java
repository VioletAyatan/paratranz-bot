package com.para.tranzai.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
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
}
