package org.paratranz.bot.bot.core;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.util.Arrays;
import java.util.function.BiConsumer;

public abstract class GroupMessageCommandProcessor implements BiConsumer<GroupMessageEvent, String[]> {
    /**
     * 插件功能描述
     */
    private final String description;
    /**
     * 触发此指令的关键字
     */
    private final String[] key;

    protected GroupMessageCommandProcessor(String description, String[] key) {
        this.description = description;
        this.key = key;
    }

    @Override
    public void accept(GroupMessageEvent event, String[] args) {
        if (args.length > 1) {
            onArgsEvent(event, Arrays.copyOfRange(args, 1, args.length));
        } else {
            onNoArgsEvent(event);
        }
    }

    /**
     * 无参数调用的情况下触发此方法
     *
     * @param event Bot事件对象（继承自{@link MessageEvent}）
     */
    protected void onNoArgsEvent(GroupMessageEvent event) {

    }

    /**
     * 有参数调用的情况下触发此方法
     *
     * @param event Bot事件对象（继承自{@link MessageEvent}）
     * @param args  指令集参数，将会自动将传入指令作为数组分发.
     */
    protected void onArgsEvent(GroupMessageEvent event, String[] args) {

    }

    public String getDescription() {
        return description;
    }

    public String[] getKey() {
        return key;
    }
}
