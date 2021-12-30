package com.para.tranzai.para.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 术语
 */
@NoArgsConstructor
@Data
public class Terms {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("updatedBy")
    private UpdatedByDTO updatedBy;
    @SerializedName("pos")
    private String pos;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("term")
    private String term;
    @SerializedName("translation")
    private String translation;
    @SerializedName("note")
    private String note;
    @SerializedName("project")
    private Integer project;
    @SerializedName("variants")
    private List<String> variants;
    @SerializedName("commentsCount")
    private Integer commentsCount;
    @SerializedName("user")
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class UpdatedByDTO {
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
