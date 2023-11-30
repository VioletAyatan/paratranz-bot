package org.paratranz.bot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "system")
public class SystemProperties {
    /**
     * 模块控制，调试用，用以控制系统各模块激活
     */
    private ModuleControl moduleControl;

    public ModuleControl getModuleControl() {
        return moduleControl;
    }

    public void setModuleControl(ModuleControl moduleControl) {
        this.moduleControl = moduleControl;
    }

    public static class ModuleControl {
        /**
         * 影响系统中有关mirai相关服务的启停
         */
        private Boolean miraiActive = false;

        public Boolean getMiraiActive() {
            return miraiActive;
        }

        public void setMiraiActive(Boolean miraiActive) {
            this.miraiActive = miraiActive;
        }
    }
}
