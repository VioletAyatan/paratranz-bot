package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目成员
 */
@NoArgsConstructor
@Data
public class Member {

    
    private Integer id;
    
    private String createdAt;
    
    private Integer uid;
    
    private Integer project;
    
    private Integer permission;
    
    private Integer translated;
    
    private Integer edited;
    
    private Integer reviewed;
    
    private Double points;
    
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        
        private Integer id;
        
        private String username;
        
        private String avatar;
        
        private String lastVisit;
        
        private Boolean isOnline;
    }
}
