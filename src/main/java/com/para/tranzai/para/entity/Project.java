package com.para.tranzai.para.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 项目信息
 */
@Data
@NoArgsConstructor
public class Project {

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
        @SerializedName("compatible")
        private String compatible;
        @SerializedName("creditLink")
        private String creditLink;
        @SerializedName("hasTranslation")
        private Boolean hasTranslation;
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
}
