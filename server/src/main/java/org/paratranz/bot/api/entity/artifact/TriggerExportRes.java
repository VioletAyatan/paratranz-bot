package org.paratranz.bot.api.entity.artifact;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TriggerExportRes {
    private Integer project;
    private String type;
    private Integer uid;
    private Integer id;
    private String createdAt;
    private Object startedAt;
    private Object finishedAt;
    private Object scheduledAt;
    private Integer status;
    private Object result;
    private Object params;
}
