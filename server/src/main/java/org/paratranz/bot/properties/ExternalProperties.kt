package org.paratranz.bot.properties

import lombok.Data
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Data
@Component
@PropertySource(value = ["file:config/config.properties"])
class ExternalProperties {
    /**
     * bot qq账号
     */
    @Value("{tranz.bot.number}")
    var qq: Long? = null

    /**
     * bot qq密码
     */
    @Value("{tranz.bot.password}")
    var password: String? = null

    /**
     * 配置接收到paraTranz项目申请后机器人会转发消息到哪些群组.
     */
    @Value("{tranz.bot.groups}")
    var groups: Array<Long>? = null

    /**
     * 配置机器人监听的paraTranz项目id
     */
    @Value("{tranz.projectId}")
    var projectId: Int? = null
}
