package org.paratranz.bot.api.entity.terms;

import lombok.Data;
import org.paratranz.bot.api.entity.common.PrUser;

@Data
public class TermDetail extends TermConfigRes {
    /**
     * 用户信息
     */
    private PrUser user;
    /**
     * 评论数量
     */
    private Integer commentsCount;
}
