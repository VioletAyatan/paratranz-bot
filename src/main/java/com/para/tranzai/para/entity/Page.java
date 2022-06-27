package com.para.tranzai.para.entity;

import lombok.Data;

/**
 * 分页参数类
 * 用于在调用paraTranzApi时传递分页参数使用.
 * @author ankol
 */
@Data
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

    /**
     * 创建一个page类，从第一页开始，每页包含50条信息
     */
    public Page() {
        this.page = 1;
        this.pageSize = 50;
    }
}
