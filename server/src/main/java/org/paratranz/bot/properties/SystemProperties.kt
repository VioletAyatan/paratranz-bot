package org.paratranz.bot.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "system")
class SystemProperties {
    /**
     * 模块控制，调试用，用以控制系统各模块激活
     */
    var moduleControl: ModuleControl = ModuleControl()

    class ModuleControl {
        /**
         * 影响系统中有关mirai相关服务的启停
         */
        var miraiActive: Boolean = false
    }
}
