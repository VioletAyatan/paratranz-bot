package org.paratranz.bot.bot.core;

import java.util.HashMap;
import java.util.Optional;

/**
 * 指令管理器
 * 通过此处理器进行指令处理分发或添加.
 *
 * @author ankol
 */
public class CommandManger {

    private static final HashMap<String, GroupMessageCommandProcessor> PROCESSOR_HASH_MAP = new HashMap<>();

    public static Optional<GroupMessageCommandProcessor> getIfPresent(String command) {
        return Optional.ofNullable(PROCESSOR_HASH_MAP.get(command));
    }

    /**
     * Register command processor.
     *
     * @param commands         触发此处理器的指令
     * @param commandProcessor 指令处理器
     */
    public static void registerCommandProcessor(String[] commands, GroupMessageCommandProcessor commandProcessor) {
        for (String command : commands) {
            PROCESSOR_HASH_MAP.put(command, commandProcessor);
        }
    }

}
