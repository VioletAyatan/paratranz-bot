package com.para.tranzai.schedules;

import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.Application;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.TranzaiProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Scheduled(cron = "")
    public void schedulingApplication() {
        PageResult<Application> pageResult = paraService.listApplications(properties.getProjectId());
        // 待审核列表.
        List<Application> collect = pageResult.getResults()
                .stream()
                //状态0为待审核.
                .filter(item -> item.getStatus() == 0)
                .collect(Collectors.toList());

        if (collect.isEmpty()) {
            for (Long id : properties.getMiraiBotConfig().getGroups()) {
                Group group = bot.getGroup(id);
                if (group != null) {
                    group.sendMessage(buildMessage());
                }
            }
        }
    }

    private MessageChain buildMessage() {
        return null;
    }

}
