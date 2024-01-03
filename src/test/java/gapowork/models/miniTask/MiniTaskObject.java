package gapowork.models.miniTask;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gapowork.models.media.AttachmentFileObject;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class MiniTaskObject {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("assignees")
    private List<Integer> assignees;

    @JsonProperty("watchers")
    private List<Integer> watchers;

    @JsonProperty("due_date")
    private Long due_date;

    @JsonProperty("status")
    private int status;

    @JsonProperty("priority")
    private int priority;

    @JsonProperty("attachment_files")
    private List<AttachmentFileObject> attachment_files;

    public MiniTaskObject(String title,
                          String description, List<Integer> assignees,
                          List<Integer> watchers, Long due_date, int status, int priority, List<AttachmentFileObject> attachment_files) {
        this.title = title;
        this.description = description;
        this.assignees = assignees;
        this.watchers = watchers;
        this.due_date = due_date;
        this.status = status;
        this.priority = priority;
        this.attachment_files = attachment_files;
    }

    public MiniTaskObject() {
    }
}


