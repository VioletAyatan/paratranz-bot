package org.paratranz.bot.api.entity.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件
 */
@NoArgsConstructor
@Data
public class File {

    @SerializedName("id")
    private Integer id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("name")
    private String name;
    @SerializedName("project")
    private Integer project;
    @SerializedName("format")
    private String format;
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
    @SerializedName("extra")
    private Object extra;
    @SerializedName("folder")
    private String folder;
    @SerializedName("progress")
    private ProgressDTO progress;

    @NoArgsConstructor
    @Data
    public static class ProgressDTO {
        @SerializedName("translate")
        private Double translate;
        @SerializedName("review")
        private Double review;
        @SerializedName("check")
        private Double check;
    }
}
