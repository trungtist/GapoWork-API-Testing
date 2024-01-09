package gapowork.models.miniTask;

import gapowork.helper.Helper;
import lombok.Data;

import java.util.List;

public @Data class ProjectObject {
    private String name;
    private String description;
    private boolean content_rtf;
    private String color;
    private String icon;
    private List<Integer> members;
    private List<String> department_ids;
    private List<String> role_ids;
    private List<String> thread_ids;
    private List<String> ignore_user_ids;

    public ProjectObject() {}

    // Create project with name
    public ProjectObject(String name) {
        this.name = name;
        this.color = Helper.getRandomColor();
        this.icon = Helper.getRandomIcon();
    }

    public ProjectObject(String name, List<Integer> members, List<String> department_ids, List<String> role_ids, List<String> thread_ids) {
        this.name = name;
        this.color = Helper.getRandomColor();
        this.icon = Helper.getRandomIcon();
        this.members = members;
        this.department_ids = department_ids;
        this.role_ids = role_ids;
        this.thread_ids = thread_ids;
    }
}
