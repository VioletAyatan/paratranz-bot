package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Audit {

    private Integer id;
    
    private String key;
    
    private String original;
    
    private String translation;
    
    private OriginDTO origin;

    @NoArgsConstructor
    @Data
    public static class OriginDTO {
        
        private Integer id;
        
        private String createdAt;
        
        private String updatedAt;
        
        private String key;
        
        private String original;
        
        private String translation;
        
        private Integer file;
        
        private Integer stage;
        
        private Integer project;
        
        private Integer uid;
        
        private Object extra;
        
        private Object context;
        
        private Integer words;
        
        private UserDTO user;
        
        private Integer fileId;

        @NoArgsConstructor
        @Data
        public static class UserDTO {
            
            private Integer id;
            
            private String username;
            
            private String nickname;
            
            private String avatar;
            
            private String lastVisit;
        }
    }
}
