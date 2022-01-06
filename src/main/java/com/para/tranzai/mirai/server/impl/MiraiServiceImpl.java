package com.para.tranzai.mirai.server.impl;

import com.para.tranzai.mirai.exception.NoFriendFoundException;
import com.para.tranzai.mirai.exception.NoGroupFoundException;
import com.para.tranzai.mirai.server.MiraiService;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.Message;

import javax.annotation.ManagedBean;

@Slf4j
@ManagedBean
public class MiraiServiceImpl implements MiraiService {

    private final Bot bot;

    public MiraiServiceImpl(Bot bot) {
        this.bot = bot;
    }

    @Override
    public MessageReceipt<Group> sendGroupMessage(Long id, Message message) {
        return getGroup(id).sendMessage(message);
    }

    @Override
    public MessageReceipt<Group> sendGroupMessage(Long id, String message) {
        return getGroup(id).sendMessage(message);
    }

    @Override
    public MessageReceipt<Friend> sendFriendMessage(Long id, Message message) {
        return getFriend(id).sendMessage(message);
    }

    @Override
    public MessageReceipt<Friend> sendFriendMessage(Long id, String message) {
        return getFriend(id).sendMessage(message);
    }

    private Group getGroup(Long id) {
        Group group = bot.getGroup(id);
        if (group == null) {
            throw new NoGroupFoundException();
        }
        return group;
    }

    private Friend getFriend(Long id) {
        Friend friend = bot.getFriend(id);
        if (friend == null) {
            throw new NoFriendFoundException();
        }
        return friend;
    }
}
