package com.para.tranzai.schedules;

import cn.hutool.core.util.StrUtil;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.Application;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.TranzaiProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApplicationSchedule {

    private final ParaService paraService;
    private final TranzaiProperties properties;

    private final Bot bot;

    public ApplicationSchedule(ParaService paraService, TranzaiProperties properties, Bot bot) {
        this.paraService = paraService;
        this.properties = properties;
        this.bot = bot;
    }

    /**
     * 此变量用于记录当前已经推送过的申请信息.（防止重复推送）
     * 每日零点清空.
     */
    private Map<Integer, Application> isChecked = new HashMap<>();

    /**
     * 定时任务，每隔5分钟检测一次申请列表，如果有待审核的申请则通知指定群组.
     */
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    public void schedulingApplication() {
        PageResult<Application> pageResult = paraService.listApplications(properties.getProjectId());
        // 待审核列表.
        List<Application> collect = pageResult.getResults()
                .stream()
                //条件，状态0为待审核.并且不包含在已推送列表中.
                .filter(item -> item.getStatus() == 0 && !isChecked.containsKey(item.getId()))
                .collect(Collectors.toList());
        //如果待审核列表不为空，执行q群通知...
        if (collect.isEmpty()) {
            for (Long id : properties.getMiraiBotConfig().getGroups()) {
                Group group = bot.getGroup(id);
                if (group != null) {
                    group.sendMessage(buildMessage(group, collect));
                }
            }
            isChecked = collect.stream().collect(Collectors.toMap(Application::getId, application -> application));
        }
    }

    /**
     * 每日0点运行
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void resetIsChecked() {
        isChecked.clear();
    }

    private MessageChain buildMessage(Group group, List<Application> collect) {
        MessageChainBuilder builder = new MessageChainBuilder();
        //如果是管理员，加入艾特全员操作.
        if (group.getBotPermission().getLevel() == MemberPermission.ADMINISTRATOR.getLevel())
            builder.append(AtAll.INSTANCE);
        for (Application application : collect) {
            Application.UserDTO user = application.getUser();
            builder.append(StrUtil.format("{} 申请加入项目组，请及时审核.", user.getUsername()));
            builder.append(StrUtil.format("游戏时间 {} 小时，英语等级：{}",
                    application.getDetail().getGameTime(), application.getDetail().getEnglish().getLevel()));
            builder.append("\n");
        }
        return builder.build();
    }

}
