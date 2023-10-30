package org.paratranz.bot.api.entity.artifact;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ArtifactResult {
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 项目id
     */
    private Integer project;
    /**
     *
     */
    private Integer total;
    private Integer translated;
    private Integer disputed;
    private Integer reviewed;
    private Integer hidden;
    private Integer size;
    private Integer duration;
}
