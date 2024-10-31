package org.paratranz.bot.api.constant;

import lombok.Getter;

@Getter
public enum ApplyStatus {
    /**
     * 待审核
     */
    UN_CONFIRM(0),
    /**
     * 已拒绝
     */
    REJECTED(1),
    /**
     * 已通过
     */
    ACCEPTED(2),
    ;
    private final int code;

    ApplyStatus(int code) {
        this.code = code;
    }
}
