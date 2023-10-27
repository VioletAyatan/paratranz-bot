package org.paratranz.bot.api.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目成员
 */
@NoArgsConstructor
@Data
public class Member {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("project")
    private Integer project;
    @SerializedName("permission")
    private Integer permission;
    @SerializedName("translated")
    private Integer translated;
    @SerializedName("edited")
    private Integer edited;
    @SerializedName("reviewed")
    private Integer reviewed;
    @SerializedName("points")
    private Double points;
    @SerializedName("user")
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("username")
        private String username;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("lastVisit")
        private String lastVisit;
        @SerializedName("isOnline")
        private Boolean isOnline;
    }
}
