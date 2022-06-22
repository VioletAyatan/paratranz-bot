package com.para.tranzai.command;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.para.tranzai.command.annotation.CommandProcessor;
import com.para.tranzai.para.entity.Page;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.data.Application;
import com.para.tranzai.para.entity.data.Audit;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.GroupMessageEvent;
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
    protected void triggerNoArgs(GroupMessageEvent event) {
        PageResult<Application> pageResult = paraService.listApplications(new Page(), properties.getProjectId(), 0);
        if (CollUtil.isNotEmpty(pageResult.getResults())) {
            List<Application> results = pageResult.getResults();
            //有多个待审核人的情况
            if (results.size() > 1) {
//                multiprocessing(event, results);
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
                this.sendMessage(event, testContent);
            }
        } else {
            event.getGroup().sendMessage("抱歉，没找到需要审核人员呢~");
        }
    }

    @Override
    protected void triggerArgsEvent(GroupMessageEvent event, String[] args) {
        String uid = args[0];
        if (StrUtil.isNotEmpty(uid)) {
            try {
                List<Audit> content = paraService.getTestContent(Integer.parseInt(uid), properties.getProjectId());
                this.sendMessage(event, content);
            } catch (NumberFormatException e) {
                event.getGroup().sendMessage("无法解析参数，确保查询参数为正确的数字用户id。例：/群审核 [用户id]");
            }
        } else {
            event.getGroup().sendMessage("查询的用户id不可为空！例：/群审核 [用户id]");
        }
    }

    private void sendMessage(GroupMessageEvent event, List<Audit> testContent) {
        for (Audit audit : testContent) {
            MessageChainBuilder builder = new MessageChainBuilder()
                    .append(audit.getOriginal()).append("\n\n");
            //旧词条翻译者用户信息可能为空
            if (audit.getOrigin().getUser() != null) {
                builder.append(audit.getOrigin().getUser().getNickname()).append("：").append("\n");
            }
            //继续拼接
            builder.append(audit.getOrigin().getTranslation()).append("\n\n")
                    .append("申请者翻译：").append("\n")
                    .append(audit.getTranslation());
            //发送消息
            event.getGroup().sendMessage(builder.build());
        }
    }
}
