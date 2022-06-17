package com.para.tranzai.tools;

import cn.hutool.core.util.StrUtil;

public class Utils {
    /**
     * 获取指令参数，调用机器人的指令参数均以空格分割.
     * @param message 消息
     * @return 参数数组
     */
    public static String[] getArgs(String message) {
        if (StrUtil.isNotBlank(message)) {
            return message.split(" ");
        }
        return new String[0];
    }
}
