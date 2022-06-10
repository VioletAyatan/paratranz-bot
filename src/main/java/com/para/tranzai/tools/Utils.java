package com.para.tranzai.tools;

import cn.hutool.core.util.StrUtil;

public class Utils {

    public static String[] getArgs(String message) {
        if (StrUtil.isNotBlank(message)) {
            return message.split(" ");
        }
        return new String[0];
    }
}
