package org.paratranz.bot.api.constant

enum class ApplyStatus(val code: Int) {
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
}
