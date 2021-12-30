package com.para.tranzai.tools;

import com.para.tranzai.para.entity.Page;
import com.para.tranzai.para.entity.PageResult;
import com.para.tranzai.para.entity.Task;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.TranzaiProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements ApplicationListener<ApplicationReadyEvent> {

    private final ParaService paraService;
    private final TranzaiProperties properties;

    public Initializer(ParaService paraService, TranzaiProperties properties) {
        this.paraService = paraService;
        this.properties = properties;
    }

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        //初始化一些必要参数...
        GlobalVariable.taskCaches = getAllTaskList();
    }

    private PageResult<Task> getAllTaskList() {
        Integer rowCount = paraService.listTasks(new Page(1, 1), properties.getProjectId()).getRowCount();
        return paraService.listTasks(new Page(1, rowCount), properties.getProjectId());
    }
}
