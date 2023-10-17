package org.paratranz.bot.api.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Announcement {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("project")
    private Integer project;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("html")
    private String html;
    @SerializedName("status")
    private Integer status;
    @SerializedName("sticky")
    private Boolean sticky;
    @SerializedName("category")
    private String category;
    @SerializedName("votesCount")
    private Integer votesCount;
    @SerializedName("commentsCount")
    private Integer commentsCount;
    @SerializedName("user")
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("username")
        private String username;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("lastVisit")
        private String lastVisit;
    }
}
