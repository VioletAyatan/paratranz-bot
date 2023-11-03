package org.paratranz.bot.bot.core;

import java.util.HashMap;

/**
 * 指令管理器
 * 通过此处理器进行指令处理分发或添加.
 *
 * @author ankol
 */
public class CommandManger {

    private static final HashMap<String, GroupMessageCommandProcessor> PROCESSOR_HASH_MAP = new HashMap<>();

    public static GroupMessageCommandProcessor getCommandProcessor(String command) {
        return PROCESSOR_HASH_MAP.get(command);
    }

    /**
     * Register command processor.
     *
     * @param commands         触发此处理器的指令
     * @param commandProcessor 指令处理器
     * @param override         如果已经存在了指令处理器，可以通过此选项指定是否覆盖现存的指令处理器
     */
    public static void registerCommandProcessor(String[] commands, GroupMessageCommandProcessor commandProcessor) {
        for (String command : commands) {
            PROCESSOR_HASH_MAP.put(command, commandProcessor);
        }
    }

}