package com.para.tranzai.para.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数类
 * 用于在调用paraTranzApi时传递分页参数使用.
 */
@Data
@NoArgsConstructor
public class Page {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 一页包含的项目数量.
     */
    private Integer pageSize;

    public Page(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
}
