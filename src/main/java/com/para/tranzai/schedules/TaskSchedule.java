package com.para.tranzai.schedules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.para.tranzai.mirai.exception.NoGroupFoundException;
import com.para.tranzai.para.entity.Page;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.Task;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.TranzaiProperties;
import com.para.tranzai.tools.GlobalVariable;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@ManagedBean
//@DependsOn("miraiBot")
public class TaskSchedule {

    private final TranzaiProperties properties;
    private final ParaService paraService;

    private final Bot bot;

    public TaskSchedule(TranzaiProperties properties, ParaService paraService, Bot bot) {
        this.properties = properties;
        this.paraService = paraService;
        this.bot = bot;
    }

    /**
     * 定时遍历指定项目项目任务列表并给指定群组推送.
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void schedulingTask() {
        Integer rowCount = paraService.listTasks(new Page(1, 1), properties.getProjectId()).getRowCount();
        PageResult<Task> newTaskList = paraService.listTasks(new Page(1, rowCount), properties.getProjectId());
        //检查是否有新的任务
        List<Task> diffedObjs = CollUtil.disjunction(newTaskList.getResults(), GlobalVariable.taskCaches.getResults())
                .stream()
                //时间检查，创建时间在5分钟内的任务才视为新发布的任务.
                .filter(item -> DateUtil.parse(item.getCreatedAt()).isAfter(DateTime.now().offset(DateField.MINUTE, 5)))
                .collect(Collectors.toUnmodifiableList());
        //执行q群推送...
        if (CollUtil.isNotEmpty(diffedObjs)) {
            for (Long id : properties.getMiraiBotConfig().getGroups()) {
                try {
                    Group group = bot.getGroup(id);
                    if (group != null) {
                        group.sendMessage(buildMessage(group, diffedObjs));
                    }
                } catch (NoGroupFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        //更新缓存.
        GlobalVariable.taskCaches = newTaskList;
    }

    private MessageChain buildMessage(Group group, Collection<Task> diffObj) {
        MessageChainBuilder builder = new MessageChainBuilder();
        //如果机器人是管理员，则加上@全体操作.
        if (group.getBotPermission().getLevel() == MemberPermission.ADMINISTRATOR.getLevel()) {
            builder.append(AtAll.INSTANCE);
        }
        for (Task task : diffObj) {
            String format = StrUtil.format("{} 发布了新任务：[{}]", task.getUser().getNickname(), task.getTitle());
            builder.append(format);
        }
        return builder.build();
    }
}
