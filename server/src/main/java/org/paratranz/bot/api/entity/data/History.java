package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目历史记录
 */
@NoArgsConstructor
@Data
public class History {

    
    private Integer id;
    
    private String createdAt;
    
    private String field;
    
    private Integer uid;
    
    private Integer tid;
    
    private Integer project;
    
    private String type;
    
    private String key;
    
    private String from;
    
    private String to;
    
    private TargetDTO target;
    
    private String operation;
    
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class TargetDTO {
        
        private Integer stage;
        
        private Integer words;
        
        private String original;
        
        private String translation;
    }

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        
        private Integer id;
        
        private String username;
        
        private String avatar;
        
        private String lastVisit;
    }
}
