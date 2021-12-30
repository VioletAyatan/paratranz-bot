package com.para.tranzai.para.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IssuePageResult<T> extends PageResult<T> {
    /**
     * 关闭的讨论数量
     */
    private Integer closedCount;
    /**
     * 打开的讨论数量
     */
    private Integer openCount;
}
