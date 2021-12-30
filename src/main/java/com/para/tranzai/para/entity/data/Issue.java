package com.para.tranzai.para.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 讨论
 */
@NoArgsConstructor
@Data
public class Issue {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("project")
    private Integer project;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("parent")
    private Object parent;
    @SerializedName("title")
    private String title;
    @SerializedName("status")
    private Integer status;
    @SerializedName("lastEdit")
    private Object lastEdit;
    @SerializedName("childrenCount")
    private Integer childrenCount;
    @SerializedName("repliedAt")
    private String repliedAt;
    @SerializedName("user")
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;
        @SerializedName("lastVisit")
        private String lastVisit;
        @SerializedName("username")
        private String username;
        @SerializedName("nickname")
        private Object nickname;
        @SerializedName("bio")
        private Object bio;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("email")
        private String email;
        @SerializedName("github")
        private Integer github;
        @SerializedName("role")
        private Integer role;
        @SerializedName("credit")
        private Integer credit;
        @SerializedName("translated")
        private Integer translated;
        @SerializedName("reviewed")
        private Integer reviewed;
        @SerializedName("commented")
        private Integer commented;
        @SerializedName("edited")
        private Integer edited;
        @SerializedName("points")
        private Double points;
    }
}
