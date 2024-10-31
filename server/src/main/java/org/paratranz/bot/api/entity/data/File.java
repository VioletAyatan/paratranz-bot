package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件
 */
@NoArgsConstructor
@Data
public class File {

    
    private Integer id;
    
    private String createdAt;
    
    private String updatedAt;
    
    private String name;
    
    private Integer project;
    
    private String format;
    
    private Integer total;
    
    private Integer translated;
    
    private Integer disputed;
    
    private Integer checked;
    
    private Integer reviewed;
    
    private Integer hidden;
    
    private Integer locked;
    
    private Integer words;
    
    private Object extra;
    
    private String folder;
    
    private ProgressDTO progress;

    @NoArgsConstructor
    @Data
    public static class ProgressDTO {
        
        private Double translate;
        
        private Double review;
        
        private Double check;
    }
}
