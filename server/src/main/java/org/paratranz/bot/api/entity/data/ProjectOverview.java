package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目概述
 * 通过插件生成的结构，请不要乱动.
 */
@Data
@NoArgsConstructor
public class ProjectOverview {

    
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
    
    private OwnerDTO owner;
    
    private StatsDTO stats;
    
    private List<String> relatedGames;
    
    private Boolean isPrivate;
    
    private String gameName;
    
    private FormatsDTO formats;
    
    private WebhookDTO webhook;
    
    private Integer announcementsCount;
    
    private Integer tasksCount;
    
    private Integer filesCount;
    
    private Integer termsCount;
    
    private Integer issuesCount;
    
    private Integer applicationsCount;
    
    private List<List<String>> tags;

    @NoArgsConstructor
    @Data
    public static class ExtraDTO {
        
        private String link;
        
        private Boolean task;
        
        private Boolean isMod;
        
        private String credit;
        
        private String version;
        
        private Integer testCount;
        
        private String compatible;
        
        private String creditLink;
        
        private String modVersion;
        
        private Boolean customTests;
        
        private String publishLink;
        
        private Boolean hasTranslation;
    }

    @NoArgsConstructor
    @Data
    public static class OwnerDTO {
        
        private Integer id;
        
        private String username;
        
        private String nickname;
        
        private String avatar;
        
        private String lastVisit;
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

    @NoArgsConstructor
    @Data
    public static class WebhookDTO {
        
        private String discord;
    }
}
