package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 讨论
 */
@NoArgsConstructor
@Data
public class Issue {

    
    private Integer id;
    
    private String createdAt;
    
    private String updatedAt;
    
    private Integer project;
    
    private Integer uid;
    
    private Object parent;
    
    private String title;
    
    private Integer status;
    
    private Object lastEdit;
    
    private Integer childrenCount;
    
    private String repliedAt;
    
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        
        private Integer id;
        
        private String createdAt;
        
        private String updatedAt;
        
        private String lastVisit;
        
        private String username;
        
        private Object nickname;
        
        private Object bio;
        
        private String avatar;
        
        private String email;
        
        private Integer github;
        
        private Integer role;
        
        private Integer credit;
        
        private Integer translated;
        
        private Integer reviewed;
        
        private Integer commented;
        
        private Integer edited;
        
        private Double points;
    }
}
