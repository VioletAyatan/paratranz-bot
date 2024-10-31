package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目信息
 */
@Data
@NoArgsConstructor
public class Project {

    
    private Integer id;
    
    private String createdAt;
    
    private String updatedAt;
    
    private Integer uid;
    
    private String name;
    
    private String logo;
    
    private String desc;
    
    private String source;
    
    private String dest;
    
    private Integer members;
    
    private String game;
    
    private String license;
    
    private Double activeLevel;
    
    private Integer rank;
    
    private Integer privacy;
    
    private Integer download;
    
    private Integer joinMode;
    
    private Integer issueMode;
    
    private Integer reviewMode;
    
    private Integer stage;
    
    private ExtraDTO extra;
    
    private StatsDTO stats;
    
    private List<String> relatedGames;
    
    private Boolean isPrivate;
    
    private String gameName;
    
    private FormatsDTO formats;

    @NoArgsConstructor
    @Data
    public static class ExtraDTO {
        
        private String link;
        
        private Boolean task;
        
        private Boolean isMod;
        
        private String credit;
        
        private String version;
        
        private String compatible;
        
        private String creditLink;
        
        private Boolean hasTranslation;
    }

    @NoArgsConstructor
    @Data
    public static class StatsDTO {
        
        private Integer id;
        
        private Integer total;
        
        private Integer translated;
        
        private Integer disputed;
        
        private Integer checked;
        
        private Integer reviewed;
        
        private Integer hidden;
        
        private Integer locked;
        
        private Integer words;
        
        private Integer members;
        
        private Double tp;
        
        private Double cp;
        
        private Double rp;
    }

    @NoArgsConstructor
    @Data
    public static class FormatsDTO {
        
        private String yml;
        
        private String txt;
    }
}
