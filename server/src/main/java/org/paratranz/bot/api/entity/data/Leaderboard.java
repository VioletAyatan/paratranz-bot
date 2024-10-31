package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排行榜
 */
@NoArgsConstructor
@Data
public class Leaderboard {

    
    private Integer id;
    
    private String username;
    
    private String nickname;
    
    private String avatar;
    
    private String lastVisit;
    
    private String createdAt;
    
    private String updatedAt;
    
    private Integer uid;
    
    private Integer project;
    
    private Integer permission;
    
    private Integer approveBy;
    
    private String note;
    
    private Integer translated;
    
    private Integer edited;
    
    private Integer reviewed;
    
    private Double points;
    
    private Boolean isOnline;
}
