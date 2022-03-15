package com.para.tranzai.para.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Audit {

    @SerializedName("id")
    private Integer id;
    @SerializedName("key")
    private String key;
    @SerializedName("original")
    private String original;
    @SerializedName("translation")
    private String translation;
    @SerializedName("origin")
    private OriginDTO origin;

    @NoArgsConstructor
    @Data
    public static class OriginDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;
        @SerializedName("key")
        private String key;
        @SerializedName("original")
        private String original;
        @SerializedName("translation")
        private String translation;
        @SerializedName("file")
        private Integer file;
        @SerializedName("stage")
        private Integer stage;
        @SerializedName("project")
        private Integer project;
        @SerializedName("uid")
        private Integer uid;
        @SerializedName("extra")
        private Object extra;
        @SerializedName("context")
        private Object context;
        @SerializedName("words")
        private Integer words;
        @SerializedName("user")
        private UserDTO user;
        @SerializedName("fileId")
        private Integer fileId;

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
}
