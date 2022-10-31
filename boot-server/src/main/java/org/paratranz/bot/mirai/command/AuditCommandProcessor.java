package org.paratranz.bot.mirai.command;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.paratranz.bot.mirai.command.annotation.CommandProcessor;
import org.paratranz.bot.mirai.command.core.AbstractCommandProcessor;
import org.paratranz.bot.para.entity.Page;
import org.paratranz.bot.para.entity.PageResult;
import org.paratranz.bot.para.entity.data.Application;
import org.paratranz.bot.para.entity.data.Audit;
import org.paratranz.bot.para.server.ParaApiService;
import org.paratranz.bot.properties.ExternalProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.util.List;


/**
 * 群审核指令处理器
 * @author Ankol
 */
@Slf4j
@RequiredArgsConstructor
@CommandProcessor("/群审核")
public class AuditCommandProcessor extends AbstractCommandProcessor<GroupMessageEvent> {

    private final ParaApiService paraApiService;
    private final ExternalProperties properties;

    @Override
    protected void onNoArgsEvent(GroupMessageEvent event) {
        PageResult<Application> pageResult = paraApiService.listApplications(new Page(), properties.getProjectId(), 0);
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
                List<Audit> testContent = paraApiService.getTestContent(application.getId(), properties.getProjectId());
                this.sendMessage(event, testContent);
            }
        } else {
            event.getGroup().sendMessage("抱歉，没找到需要审核人员呢~");
        }
    }

    @Override
    protected void onArgsEvent(GroupMessageEvent event, String[] args) {
        String applicationId = args[0];
        if (StrUtil.isNotEmpty(applicationId)) {
            try {
                List<Audit> content = paraApiService.getTestContent(Integer.parseInt(applicationId), properties.getProjectId());
                this.sendMessage(event, content);
            } catch (NumberFormatException e) {
                event.getGroup().sendMessage("无法解析参数，确保查询参数为正确的数字用户id。例：/群审核 [用户id]");
            }
        } else {
            event.getGroup().sendMessage("查询的用户id不可为空！例：/群审核 [用户id]");
        }
    }

    private void sendMessage(GroupMessageEvent event, List<Audit> testContent) {
        if (testContent.isEmpty()) {
            event.getGroup().sendMessage("未找到相关测试内容呢~");
        } else {
            for (Audit audit : testContent) {
                MessageChainBuilder builder = new MessageChainBuilder()
                        .append(audit.getOriginal()).append("\n\n");
                //旧词条翻译者用户信息可能为空
                Audit.OriginDTO.UserDTO user = audit.getOrigin().getUser();
                if (user != null) {
                    builder.append(user.getNickname() != null ? user.getNickname() : user.getUsername()).append("：").append("\n");
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
}
