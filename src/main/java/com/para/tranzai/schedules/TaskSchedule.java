package com.para.tranzai.schedules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.para.tranzai.mirai.exception.NoGroupFoundException;
import com.para.tranzai.mirai.server.MiraiService;
import com.para.tranzai.para.entity.Page;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.Task;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.TranzaiProperties;
import com.para.tranzai.tools.GlobalVariable;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
@DependsOn("miraiBot")
public class TaskSchedule {

    private final TranzaiProperties properties;

    private final ParaService paraService;
    private final MiraiService miraiService;

    public TaskSchedule(TranzaiProperties properties, ParaService paraService, MiraiService miraiService) {
        this.properties = properties;
        this.paraService = paraService;
        this.miraiService = miraiService;
    }

    /**
     * 定时遍历指定项目项目任务列表并给指定群组推送.
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void schedulingTask() {
        Integer rowCount = paraService.listTasks(new Page(1, 1), properties.getProjectId()).getRowCount();
        PageResult<Task> pageResult = paraService.listTasks(new Page(1, rowCount), properties.getProjectId());
        //只会在新任务列表数量比缓存的列表数量多的时候才认为出现了新的任务.
        if (pageResult.getRowCount() > GlobalVariable.taskCaches.getRowCount()) {
            //检查是否有新的任务
            Collection<Task> diffObj = CollUtil.disjunction(pageResult.getResults(), GlobalVariable.taskCaches.getResults());
            //执行q群推送...
            if (CollUtil.isNotEmpty(diffObj)) {
                for (Long group : properties.getMiraiBotConfig().getGroups()) {
                    try {
                        miraiService.sendGroupMessage(group, buildMessage(diffObj));
                    } catch (NoGroupFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private MessageChain buildMessage(Collection<Task> diffObj) {
        MessageChainBuilder builder = new MessageChainBuilder()
                .append(AtAll.INSTANCE);
        for (Task task : diffObj) {
            String format = StrUtil.format("{} 发布了新任务：[{}]", task.getUser().getNickname(), task.getTitle());
            builder.append(format);
        }
        return builder.build();
    }
}
