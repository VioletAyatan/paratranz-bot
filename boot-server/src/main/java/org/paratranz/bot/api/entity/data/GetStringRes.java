package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetStringRes {

    private Integer id;
    private String createdAt;
    private String updatedAt;
    private String key;
    private String original;
    private String translation;
    private FileDTO file;
    private Integer stage;
    private Integer project;
    private String uid;
    private Object extra;
    private Object context;
    private Integer words;
    private Object version;
    private List<?> comments;
    private List<ImportHistoryDTO> importHistory;
    private List<?> history;
    private Integer fileId;

    @NoArgsConstructor
    @Data
    public static class FileDTO {
        private Integer id;
        private String name;
        private Integer project;
    }

    @NoArgsConstructor
    @Data
    public static class ImportHistoryDTO {
        private Integer id;
        private String createdAt;
        private String field;
        private Object uid;
        private Integer tid;
        private Integer project;
        private String key;
        private String from;
        private String to;
        private String type;
        private String operation;
    }
}
