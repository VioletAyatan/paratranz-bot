package org.paratranz.bot.api.entity.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;


@NoArgsConstructor
@Data
public class Task {
    private Integer id;
    private String createdAt;
    private Integer uid;
    private Integer project;
    private String title;
    private String content;
    private String html;
    private Object deadline;
    private Integer status;
    private GoalDTO goal;
    private Object stuckAt;
    private UserDTO user;
    private List<TaskFilesDTO> taskFiles;

    @NoArgsConstructor
    @Data
    public static class GoalDTO {
        private Double check;
        private Double review;
        private Double translate;
    }

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        private Integer id;
        private String createdAt;
        private String updatedAt;
        private String lastVisit;
        private String username;
        private String nickname;
        private Object bio;
        private String avatar;
        private String email;
        private Integer github;
        private Integer role;
        private Integer credit;
        private Integer translated;
        private Integer reviewed;
        private Integer commented;
        private Integer edited;
        private Double points;
    }

    @NoArgsConstructor
    @Data
    public static class TaskFilesDTO {
        private Integer id;
        private String createdAt;
        private Integer mode;
        private Integer task;
        private Integer file;
        private Integer total;
        private Integer translated;
        private Integer disputed;
        private Integer checked;
        private Integer reviewed;
        private Integer hidden;
        private Integer locked;
        private Integer words;
        private TaskFilesDTO.ProgressDTO progress;

        @NoArgsConstructor
        @Data
        public static class ProgressDTO {
            private Double translate;
            private Double review;
            private Double check;
        }
    }
}

