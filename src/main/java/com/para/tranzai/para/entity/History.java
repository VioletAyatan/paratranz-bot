package com.para.tranzai.para.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目历史记录
 */
@NoArgsConstructor
@Data
public class History {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("field")
    private String field;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("tid")
    private Integer tid;
    @SerializedName("project")
    private Integer project;
    @SerializedName("type")
    private String type;
    @SerializedName("key")
    private String key;
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("target")
    private TargetDTO target;
    @SerializedName("operation")
    private String operation;
    @SerializedName("user")
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class TargetDTO {
        @SerializedName("stage")
        private Integer stage;
        @SerializedName("words")
        private Integer words;
        @SerializedName("original")
        private String original;
        @SerializedName("translation")
        private String translation;
    }

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
    }
}
