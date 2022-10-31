package org.paratranz.bot.para.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排行榜
 */
@NoArgsConstructor
@Data
public class Leaderboard {

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
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("project")
    private Integer project;
    @SerializedName("permission")
    private Integer permission;
    @SerializedName("approveBy")
    private Integer approveBy;
    @SerializedName("note")
    private String note;
    @SerializedName("translated")
    private Integer translated;
    @SerializedName("edited")
    private Integer edited;
    @SerializedName("reviewed")
    private Integer reviewed;
    @SerializedName("points")
    private Double points;
    @SerializedName("isOnline")
    private Boolean isOnline;
}
