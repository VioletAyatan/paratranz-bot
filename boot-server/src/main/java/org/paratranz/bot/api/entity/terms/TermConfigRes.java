package org.paratranz.bot.api.entity.terms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TermConfigRes extends TermConfig {
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 更新时间
     */
    private String updatedAt;
    /**
     * 更新用户
     */
    private Integer updatedBy;
    /**
     * 用户Id
     */
    private Integer uid;
    /**
     * 项目Id
     */
    private Integer project;
}
