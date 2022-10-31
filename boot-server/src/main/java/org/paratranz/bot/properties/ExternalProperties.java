package org.paratranz.bot.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource({"file:config/config.properties"})
public class ExternalProperties {
    /**
     * bot qq账号
     */
    @Value("${tranz.bot.number}")
    private Long qq;
    /**
     * bot qq密码
     */
    @Value("${tranz.bot.password}")
    private String password;
    /**
     * 配置接收到paraTranz项目申请后机器人会转发消息到哪些群组.
     */
    @Value("${tranz.bot.groups}")
    private Long[] groups;
    /**
     * 配置机器人监听的paraTranz项目id
     */
    @Value("${tranz.projectId}")
    private String projectId;
}
