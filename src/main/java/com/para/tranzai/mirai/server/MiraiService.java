package com.para.tranzai.mirai.server;

import com.para.tranzai.mirai.exception.NoFriendFoundException;
import com.para.tranzai.mirai.exception.NoGroupFoundException;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.Message;

public interface MiraiService {
    /**
     * 发送群消息
     * @param id 群号
     * @param message 消息
     * @throws NoGroupFoundException 没找到此群时抛出
     */
    MessageReceipt<Group> sendGroupMessage(Long id, Message message) throws NoGroupFoundException;

    /**
     * 发送群消息
     * @param id 群号
     * @param message 消息
     * @throws NoGroupFoundException 没找到此群时抛出
     */
    MessageReceipt<Group> sendGroupMessage(Long id, String message) throws NoGroupFoundException;

    /**
     * 发送好友消息
     * @param id 好友qq
     * @param message 消息
     */
    MessageReceipt<Friend> sendFriendMessage(Long id, Message message) throws NoFriendFoundException;

    /**
     * 发送好友消息
     * @param id 好友qq
     * @param message 消息
     */
    MessageReceipt<Friend> sendFriendMessage(Long id, String message) throws NoFriendFoundException;
}
