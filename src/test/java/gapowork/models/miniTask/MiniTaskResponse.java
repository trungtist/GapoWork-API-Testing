package gapowork.models.miniTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gapowork.models.media.AttachmentFileObject;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class MiniTaskResponse {
    @JsonProperty("id")
    private String taskId;
    @JsonProperty("workspace_id")
    private String workspace_id;
    private String title;
    private Description description;
    private List<Integer> assignees;
    private List<Integer> watchers;
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
        private Integer creatorId;
        @JsonProperty("display_name")
        private String display_name;
    }
}
