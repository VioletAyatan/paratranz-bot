package org.paratranz.bot.api.entity.terms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.paratranz.bot.api.entity.common.PrUser;

@Getter
@Setter
@ToString
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
