package org.paratranz.bot.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * paraTranz返回的数据，如果有分页的情况下会使用此对象包裹.
 * @param <T> 泛型.
 * @author ankol
 */
@Data
@NoArgsConstructor
public class PageResult<T> {
    /**
     * 当前所在页
     */
    private Integer page = 0;
    /**
     * 总共有多少页
     */
    private Integer pageCount = 0;
    /**
     * 一页包含的内容数量
     */
    private Integer pageSize = 0;
    /**
     * 真正的数据内容
     */
    private List<T> results = Collections.emptyList();
    /**
     * 内容总数量
     */
    private Integer rowCount = 0;
}
