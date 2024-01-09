package gapowork.models.miniTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gapowork.models.media.AttachmentFileObject;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class TaskResponse {
    @JsonProperty("id")
    private String taskId;
    @JsonProperty("workspace_id")
    private String workspace_id;
    private String title;
    private Description description;
    private List<Assignees> assignees;
    private List<Watchers> watchers;
    private Long due_date;
    private Integer status;
    private Integer priority;
    private List<AttachmentFileObject> attachment_files;
    private Creator creator;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static @Data class Description {
        private String text;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static @Data class Creator {
        @JsonProperty("id")
        private Integer creator_id;
        @JsonProperty("display_name")
        private String display_name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static @Data class Assignees {
        @JsonProperty("id")
        private Integer assignee_id;
        @JsonProperty("display_name")
        private String display_name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static @Data class Watchers {
        @JsonProperty("id")
        private Integer watcher_id;
        @JsonProperty("display_name")
        private String display_name;
    }
}
