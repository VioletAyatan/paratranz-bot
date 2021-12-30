package com.para.tranzai.para.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
