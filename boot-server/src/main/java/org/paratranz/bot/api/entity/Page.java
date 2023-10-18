package org.paratranz.bot.api.entity;

import lombok.Data;

/**
 * 分页参数类
 * 用于在调用paraTranzApi时传递分页参数使用.
 *
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
     * 创建一个分页对象
     * @param page     当前页
     * @param pageSize 条数
     * @return {@link Page} 分页对象
     */
    public static Page of(int page, int pageSize) {
        return new Page(page, pageSize);
    }

    /**
     * 创建一个分页对象
     * 使用默认的分页配置 page=1、pageSize=50
     * @return {@link Page} 分页对象
     */
    public static Page of() {
        return new Page(1, 50);
    }
}
