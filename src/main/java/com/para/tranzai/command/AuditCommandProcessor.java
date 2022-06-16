package com.para.tranzai.command;

import cn.hutool.core.collection.CollUtil;
import com.para.tranzai.command.core.AbstractCommandProcessor;
import com.para.tranzai.command.core.CommandProcessor;
import com.para.tranzai.para.entity.Page;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.Application;
import com.para.tranzai.para.entity.data.Audit;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.util.List;

/**
 * 群审核指令处理器
 */
@Slf4j
@CommandProcessor("/群审核")
public class AuditCommandProcessor extends AbstractCommandProcessor<GroupMessageEvent> {

    private final ParaService paraService;
    private final SystemProperties properties;

    public AuditCommandProcessor(ParaService paraService, SystemProperties properties) {
        this.paraService = paraService;
        this.properties = properties;
    }

    @Override
    public void accept(GroupMessageEvent event, String[] args) {
        PageResult<Application> pageResult = paraService.listApplications(new Page(), properties.getProjectId(), 0);
        if (CollUtil.isNotEmpty(pageResult.getResults())) {
            List<Application> results = pageResult.getResults();
            if (results.size() > 1) {
                log.error("Error. application count > 1, {}", results);
            }
            //针对只有一个需要审核的人的情况.
            else {
                Application application = results.get(0);
                //状态0为待审核.这里做一个申请状态验证.
                if (application.getStatus() != 0) {
                    return;
                }
                //获取其测试内容
                List<Audit> testContent = paraService.getTestContent(application.getId(), properties.getProjectId());
                for (Audit audit : testContent) {
                    MessageChain messages = new MessageChainBuilder()
                            .append(audit.getOriginal()).append("\n")
                            .append(audit.getTranslation())
                            .build();
                    event.getGroup().sendMessage(messages);
                }
            }
        }
    }
}
