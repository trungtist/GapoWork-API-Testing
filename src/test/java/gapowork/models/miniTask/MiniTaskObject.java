package gapowork.models.miniTask;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gapowork.models.media.AttachmentFileObject;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MiniTaskObject {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("assignees")
    private List<Integer> assignees;

    @JsonProperty("watchers")
    private List<Integer> watchers;

    @JsonProperty("due_date")
    private long due_date;

    @JsonProperty("status")
    private int status;

    @JsonProperty("priority")
    private int priority;

    @JsonProperty("attachment_files")
    private List<AttachmentFileObject> attachment_files;

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
//
    public MiniTaskObject () {}

}


