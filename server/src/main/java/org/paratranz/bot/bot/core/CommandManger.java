package org.paratranz.bot.bot.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * 指令管理器
 * 通过此处理器进行指令处理分发或添加.
 *
 * @author ankol
 */
public class CommandManger {

    private static final HashMap<String, GroupMessageCommandProcessor> PROCESSOR_HASH_MAP = new HashMap<>();

    /**
     * 根据给定指令获取插件处理器
     *
     * @param command 指令参数
     * @return {@link Optional<GroupMessageCommandProcessor>}
     */
    public static Optional<GroupMessageCommandProcessor> getIfPresent(String command) {
        return Optional.ofNullable(PROCESSOR_HASH_MAP.get(command));
    }

    /**
     * 根据给定的指令注册相应的指令处理器
     *
     * @param commands         触发此处理器的指令集
     * @param commandProcessor 指令处理器
     */
    public static void registerCommandProcessor(String[] commands, GroupMessageCommandProcessor commandProcessor) {
        for (String command : commands) {
            PROCESSOR_HASH_MAP.put(command, commandProcessor);
        }
    }

    /**
     * 获取所有指令处理器
     *
     * @return {@link List<GroupMessageCommandProcessor>} 一个包含所有指令处理器的List
     */
    public static List<GroupMessageCommandProcessor> getCommandProcessors() {
        return new HashSet<>(PROCESSOR_HASH_MAP.values()).stream().toList();
    }
}
