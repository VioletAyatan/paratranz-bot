package org.paratranz.bot.api.entity.data;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.paratranz.bot.api.entity.common.PrUser;

/**
 * 贡献
 *
 * @author Ankol
 */
@Data
public class PrScore {
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private DateTime createdAt;
    /**
     * 项目ID
     */
    private String project;
    /**
     * 用户ID
     */
    private Double uid;
    /**
     * 贡献值基准，翻译为1，编辑为0.5，审核为0.2
     */
    private Double base;
    /**
     * 贡献值乘数，公式为 1 + (词数 - 1) * 0.1
     */
    private Double multiplier;
    /**
     * 基准乘以乘数得到的最终值
     */
    private Double value;
    /**
     * 操作类型（review、edit、translate）
     */
    private String operation;
    /**
     * 用户详细信息
     */
    private PrUser user;
}
