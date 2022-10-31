package org.paratranz.bot.para.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IssuePageResult<T> extends PageResult<T> {
    /**
     * 关闭的讨论数量
     */
    private Integer closedCount = 0;
    /**
     * 打开的讨论数量
     */
    private Integer openCount = 0;
}
