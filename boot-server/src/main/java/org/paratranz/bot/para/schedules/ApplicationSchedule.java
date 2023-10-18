package org.paratranz.bot.para.schedules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.paratranz.bot.api.ParaTranzApi;
import org.paratranz.bot.api.entity.PageResult;
import org.paratranz.bot.api.entity.data.Application;
import org.paratranz.bot.properties.ExternalProperties;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ApplicationSchedule {
    private final ParaTranzApi paraTranzApi;
    private final ExternalProperties externalProperties;
    private final Bot bot;
    /**
     * 此变量用于记录当前已经推送过的申请信息（防止重复推送）.
     * 每日零点清空(也就意味着如果一个申请一天都没人处理，那么第二天会重新进行推送).
     */
    private Set<Integer> isChecked = new HashSet<>();

    /**
     * 定时任务，每隔2分钟检测一次申请列表，如果有待审核的申请则通知指定群组.
     */
    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.MINUTES)
    public void schedulingApplication() {
        PageResult<Application> pageResult = paraTranzApi.apply.listApply(externalProperties.getProjectId());
        // 待审核列表.
        List<Application> applicationList = pageResult.getResults()
                .stream()
                //条件，状态0为待审核.并且不包含在已推送列表中.
                .filter(item -> item.getStatus() == 0 && !isChecked.contains(item.getId()))
                .collect(Collectors.toList());
        //如果待审核列表不为空，执行q群通知...
        if (CollUtil.isNotEmpty(applicationList)) {
            log.info("New application detected. send to group [{}]", Arrays.toString(externalProperties.getGroups()));
            for (Long groupId : externalProperties.getGroups()) {
                Group group = bot.getGroup(groupId);
                if (group != null) {
                    for (Application application : applicationList) {
                        group.sendMessage(buildMessage(group, application));
                    }
                }
            }
            isChecked = applicationList.stream().map(Application::getId).collect(Collectors.toSet());
        }
    }

    /**
     * 每日0点运行
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void resetChecked() {
        isChecked.clear();
    }

    private MessageChain buildMessage(Group group, Application application) {
        MessageChainBuilder builder = new MessageChainBuilder();
        //如果是管理员，加入艾特全员操作.
        if (group.getBotPermission().getLevel() == MemberPermission.ADMINISTRATOR.getLevel()) {
            builder.append(AtAll.INSTANCE).append("\n");
        }
        Application.UserDTO user = application.getUser();
        builder.append(StrUtil.format("{} 申请加入项目组，请及时审核\n", user.getUsername()));
//            builder.append(StrUtil.format("游戏时间 {}，英语等级：{}\n",
//                    application.getDetail().getGameTime(),
//                    application.getDetail().getEnglish().getLevel())
//            );
        if (StrUtil.isNotBlank(application.getContent())) {
            builder.append(StrUtil.format(application.getContent()));
        }
        return builder.build();
    }

}
