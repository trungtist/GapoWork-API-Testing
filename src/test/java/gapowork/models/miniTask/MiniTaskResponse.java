package gapowork.models.miniTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gapowork.models.media.AttachmentFileObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MiniTaskResponse {
    @JsonProperty("id")
    private String taskId;
    @JsonProperty("workspace_id")
    private String workspace_id;
    private String title;
    private String description;
    private List<Integer> assignees;
    private List<Integer> watchers;
    private Long due_date;
    private Integer status;
    private Integer priority;
    private List<AttachmentFileObject> attachment_files;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getWorkspace_id() {
        return workspace_id;
    }

    public void setWorkspace_id(String workspace_id) {
        this.workspace_id = workspace_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Integer> assignees) {
        this.assignees = assignees;
    }

    public List<Integer> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<Integer> watchers) {
        this.watchers = watchers;
    }

    public long getDue_date() {
        return due_date;
    }

    public void setDue_date(long due_date) {
        this.due_date = due_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<AttachmentFileObject> getAttachment_files() {
        return attachment_files;
    }

    public void setAttachment_files(List<AttachmentFileObject> attachment_files) {
        this.attachment_files = attachment_files;
    }
}
