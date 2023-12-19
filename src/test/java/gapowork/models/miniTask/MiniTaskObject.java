package gapowork.models.miniTask;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gapowork.models.media.AttachmentFileObject;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class MiniTaskObject {
    @JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    @JsonProperty("assignees")
    public List<Integer> assignees;

    @JsonProperty("watchers")
    public List<Integer> watchers;

    @JsonProperty("due_date")
    public long due_date;

    @JsonProperty("status")
    public int status;

    @JsonProperty("priority")
    public int priority;

    @JsonProperty("attachment_files")
    public List<AttachmentFileObject> attachment_files;

    public MiniTaskObject (String title, String description, List<Integer> assignees, List<Integer> watchers, long due_date, int status, int priority, List<AttachmentFileObject> attachment_files) {
        this.title = title;
        this.description = description;
        this.assignees = assignees;
        this.watchers = watchers;
        this.due_date = due_date;
        this.status = status;
        this.priority = priority;
        this.attachment_files = attachment_files;
    }

    public MiniTaskObject () {}

}
