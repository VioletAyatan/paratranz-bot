package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Announcement {

    private Integer id;
    private String createdAt;
    private String updatedAt;
    private Integer uid;
    private Integer project;
    private String title;
    private String content;
    private String html;
    private Integer status;
    private Boolean sticky;
    private String category;
    private Integer votesCount;
    private Integer commentsCount;
    private UserDTO user;

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
