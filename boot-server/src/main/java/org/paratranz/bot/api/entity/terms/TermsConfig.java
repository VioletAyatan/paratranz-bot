package org.paratranz.bot.api.entity.terms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于创建术语的配置对象
 *
 * @author Administrator
 */
@NoArgsConstructor
@Data
public class TermsConfig {
    /**
     * 词性（part of speech）
     */
    private String pos;
    /**
     * 术语原文，单词或短语
     */
    private String term;
    /**
     * 术语译文
     */
    private String translation;
    /**
     * 额外的说明
     */
    private String note;
    /**
     * 术语变体
     */
    private String[] variants;

    /**
     * 创建一个术语配置对象
     *
     * @param pos         词性枚举，参考: {@link PosEnum}
     * @param term        术语原文，单词或短语
     * @param translation 术语译文
     * @param note        额外的说明
     * @param variants    术语变体
     */
    public TermsConfig(PosEnum pos, String term, String translation, String note, String[] variants) {
        this.pos = pos.name().toLowerCase();
        this.term = term;
        this.translation = translation;
        this.note = note;
        this.variants = variants;
    }

    /**
     * 创建一个术语配置对象
     *
     * @param pos         词性
     * @param term        术语原文，单词或短语
     * @param translation 术语译文
     * @param note        额外的说明
     * @param variants    术语变体
     */
    public TermsConfig(String pos, String term, String translation, String note, String[] variants) {
        this.pos = pos;
        this.term = term;
        this.translation = translation;
        this.note = note;
        this.variants = variants;
    }

    /**
     * 词性枚举
     */
    public enum PosEnum {
        /**
         * 名词
         */
        NOUN,
        /**
         * 动词
         */
        VERB,
        /**
         * 形容词
         */
        ADJ,
        /**
         * 副词
         */
        ADV,
        ;
    }
}
