package org.paratranz.bot.api.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请
 */
@NoArgsConstructor
@Data
public class Application {

    @SerializedName("id")
    private Integer id;
    /**
     * 创建时间
     */
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("uid")
    private Integer uid;
    /**
     * 申请的项目ID
     */
    @SerializedName("project")
    private Integer project;
    @SerializedName("permission")
    private Integer permission;
    /**
     * 申请内容
     */
    @SerializedName("content")
    private String content;
    @SerializedName("status")
    private Integer status;
    @SerializedName("operator")
    private OperatorDTO operator;
    @SerializedName("reason")
    private String reason;
    @SerializedName("detail")
    private DetailDTO detail;
    @SerializedName("tests")
    private Integer tests;
    /**
     * 用户相关信息
     */
    @SerializedName("user")
    private UserDTO user;

    @NoArgsConstructor
    @Data
    public static class OperatorDTO {
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
    public static class DetailDTO {
        @SerializedName("english")
        private EnglishDTO english;
        @SerializedName("gameTime")
        private Integer gameTime;
        @SerializedName("experience")
        private ExperienceDTO experience;

        @NoArgsConstructor
        @Data
        public static class EnglishDTO {
            @SerializedName("level")
            private String level;
            @SerializedName("detail")
            private String detail;
        }

        @NoArgsConstructor
        @Data
        public static class ExperienceDTO {
            @SerializedName("times")
            private Integer times;
            @SerializedName("detail")
            private String detail;
        }
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
