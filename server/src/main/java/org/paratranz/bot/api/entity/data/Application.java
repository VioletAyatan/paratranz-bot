package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请
 */
@NoArgsConstructor
@Data
public class Application {


    private Integer id;
    /**
     * 创建时间
     */
    
    private String createdAt;
    
    private Integer uid;
    /**
     * 申请的项目ID
     */
    
    private Integer project;
    
    private Integer permission;
    /**
     * 申请内容
     */
    
    private String content;
    
    private Integer status;
    
    private OperatorDTO operator;
    
    private String reason;
    
    private DetailDTO detail;
    
    private Integer tests;
    /**
     * 用户相关信息
     */
    
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class OperatorDTO {
        
        private Integer id;
        
        private String username;
        
        private String nickname;
        
        private String avatar;
        
        private String lastVisit;
    }

    @NoArgsConstructor
    @Data
    public static class DetailDTO {
        
        private EnglishDTO english;
        
        private Integer gameTime;
        
        private ExperienceDTO experience;

        @NoArgsConstructor
        @Data
        public static class EnglishDTO {
            
            private String level;
            
            private String detail;
        }

        @NoArgsConstructor
        @Data
        public static class ExperienceDTO {
            
            private Integer times;
            
            private String detail;
        }
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