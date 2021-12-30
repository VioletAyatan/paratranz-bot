package com.para.tranzai.para.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目概述
 * 通过插件生成的结构，请不要乱动.
 */
@Data
@NoArgsConstructor
public class ProjectOverview {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("desc")
    private String desc;
    @SerializedName("source")
    private String source;
    @SerializedName("dest")
    private String dest;
    @SerializedName("members")
    private Integer members;
    @SerializedName("game")
    private String game;
    @SerializedName("license")
    private String license;
    @SerializedName("activeLevel")
    private Double activeLevel;
    @SerializedName("rank")
    private Integer rank;
    @SerializedName("privacy")
    private Integer privacy;
    @SerializedName("download")
    private Integer download;
    @SerializedName("joinMode")
    private Integer joinMode;
    @SerializedName("issueMode")
    private Integer issueMode;
    @SerializedName("reviewMode")
    private Integer reviewMode;
    @SerializedName("stage")
    private Integer stage;
    @SerializedName("extra")
    private ExtraDTO extra;
    @SerializedName("owner")
    private OwnerDTO owner;
    @SerializedName("stats")
    private StatsDTO stats;
    @SerializedName("relatedGames")
    private List<String> relatedGames;
    @SerializedName("isPrivate")
    private Boolean isPrivate;
    @SerializedName("gameName")
    private String gameName;
    @SerializedName("formats")
    private FormatsDTO formats;
    @SerializedName("webhook")
    private WebhookDTO webhook;
    @SerializedName("announcementsCount")
    private Integer announcementsCount;
    @SerializedName("tasksCount")
    private Integer tasksCount;
    @SerializedName("filesCount")
    private Integer filesCount;
    @SerializedName("termsCount")
    private Integer termsCount;
    @SerializedName("issuesCount")
    private Integer issuesCount;
    @SerializedName("applicationsCount")
    private Integer applicationsCount;
    @SerializedName("tags")
    private List<List<String>> tags;

    @NoArgsConstructor
    @Data
    public static class ExtraDTO {
        @SerializedName("link")
        private String link;
        @SerializedName("task")
        private Boolean task;
        @SerializedName("isMod")
        private Boolean isMod;
        @SerializedName("credit")
        private String credit;
        @SerializedName("version")
        private String version;
        @SerializedName("testCount")
        private Integer testCount;
        @SerializedName("compatible")
        private String compatible;
        @SerializedName("creditLink")
        private String creditLink;
        @SerializedName("modVersion")
        private String modVersion;
        @SerializedName("customTests")
        private Boolean customTests;
        @SerializedName("publishLink")
        private String publishLink;
        @SerializedName("hasTranslation")
        private Boolean hasTranslation;
    }

    @NoArgsConstructor
    @Data
    public static class OwnerDTO {
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
    public static class StatsDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("total")
        private Integer total;
        @SerializedName("translated")
        private Integer translated;
        @SerializedName("disputed")
        private Integer disputed;
        @SerializedName("checked")
        private Integer checked;
        @SerializedName("reviewed")
        private Integer reviewed;
        @SerializedName("hidden")
        private Integer hidden;
        @SerializedName("locked")
        private Integer locked;
        @SerializedName("words")
        private Integer words;
        @SerializedName("members")
        private Integer members;
        @SerializedName("tp")
        private Double tp;
        @SerializedName("cp")
        private Double cp;
        @SerializedName("rp")
        private Double rp;
    }

    @NoArgsConstructor
    @Data
    public static class FormatsDTO {
        @SerializedName("yml")
        private String yml;
        @SerializedName("txt")
        private String txt;
    }

    @NoArgsConstructor
    @Data
    public static class WebhookDTO {
        @SerializedName("discord")
        private String discord;
    }
}
