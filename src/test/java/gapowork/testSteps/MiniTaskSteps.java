package gapowork.testSteps;

import gapowork.helper.Helper;
import gapowork.models.media.AttachmentFileObject;
import gapowork.models.miniTask.ProjectObject;
import gapowork.models.miniTask.TaskObject;
import gapowork.models.miniTask.TaskResponse;
import gapowork.pages.SearchActions;
import gapowork.pages.media.UploadActions;
import gapowork.pages.miniTask.MiniTaskActions;
import gapowork.pages.miniTask.MiniTaskVerify;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;

import java.util.*;
import java.util.stream.Collectors;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;


public class MiniTaskSteps {
    @Steps
    MiniTaskActions miniTaskActions = new MiniTaskActions();
    TaskObject taskObject = new TaskObject();
    TaskResponse taskResponse = new TaskResponse();
    MiniTaskVerify miniTaskVerify = new MiniTaskVerify();
    SearchActions searchActions = new SearchActions();
    private List<AttachmentFileObject> attachmentFileObjectList = new ArrayList<>();
    List<String> task_ids;

    //  ------------------------ TASK ------------------------  //

    public static String getTaskId() {
        return Serenity.sessionVariableCalled("taskId").toString();
    }

    @When("Enter the description {string}")
    public void enter_the_description(String desc) {
        taskObject.setDescription(desc);
    }

    @When("Enter the priority {int}")
    public void enter_the_priority(int priority) {
        taskObject.setPriority(priority);
    }

    @And("Enter the status {int}")
    public void enter_the_status(int status) {
        taskObject.setStatus(status);
    }

    @SneakyThrows
    @When("Add attachments {string}")
    public void add_attachments(String url) {
        String[] arrFileUrl = Helper.splitStringToList(url);
        for (String s : arrFileUrl) {
            Response res = UploadActions.uploadFile(s);
            attachmentFileObjectList.add(res.jsonPath().getObject("", AttachmentFileObject.class));
        }
    }

    @When("Add due date")
    public void add_due_date() {
        long current_dueDate = Helper.getTimestamp();
        System.out.println("Current due date: " + current_dueDate);
        taskObject.setDue_date(current_dueDate);
    }

    @When("I add a assignee {string}")
    public void i_add_a_assignee(String assignee_key) {
        taskObject.setAssignees(searchActions.searchUserInWorkspace(assignee_key));
    }

    @And("I add a watcher {string}")
    public void i_add_a_watcher(String watcher_key) {
        taskObject.setWatchers(searchActions.searchUserInWorkspace(watcher_key));
    }

    @And("I create a task {string}")
    public void i_create_a_task(String title) {
        TaskObject miniTaskBody = new TaskObject(title,
                taskObject.getDescription(),
                taskObject.getAssignees(),
                taskObject.getWatchers(),
                taskObject.getDue_date(),
                taskObject.getStatus(),
                taskObject.getPriority(),
                attachmentFileObjectList);
        miniTaskActions.createTask(miniTaskBody);

        Serenity.setSessionVariable("taskId").to(lastResponse().body().path("data.id"));
    }

    @When("I edit the task title {string}")
    public void i_edit_the_task_title(String edited_title) {
        taskObject.setTitle(edited_title);
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @And("I edit the task description {string}")
    public void i_edit_the_task_description(String edited_description) {
        taskObject.setDescription(edited_description);
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @When("I edit the task priority {int}")
    public void i_edit_the_task_priority(int edited_priority) {
        taskObject.setPriority(edited_priority);
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @And("I edit the task status {int}")
    public void i_edit_the_task_status(int edited_status) {
        taskObject.setStatus(edited_status);
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @When("I continue to add attachment {string}")
    public void i_continue_to_add_attachment(String new_url) {
        String[] arrFileUrl = Helper.splitStringToList(new_url);
        for (String s : arrFileUrl) {
            Response res = UploadActions.uploadFile(s);
            attachmentFileObjectList.add(res.jsonPath().getObject("", AttachmentFileObject.class));
        }

        taskObject.setAttachment_files(attachmentFileObjectList);
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @When("I edit the task due date")
    public void i_edit_the_task_due_date() {
        long new_dueDate = Helper.getTimestamp() + 2;
        System.out.println("New due date: " + new_dueDate);
        taskObject.setDue_date(new_dueDate);
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @When("I continue to add assignee {string}")
    public void i_continue_to_add_assignee(String assignee_newKey) {
        taskObject.setAssignees(searchActions.searchUserInWorkspace(assignee_newKey));
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @When("I continue to add watcher {string}")
    public void i_continue_to_add_watcher(String watcher_newKey) {
        taskObject.setWatchers(searchActions.searchUserInWorkspace(watcher_newKey));
        miniTaskActions.editTask(taskObject, getTaskId());
    }

    @When("I want to view the task detail")
    public void i_want_to_view_the_task_detail() {
        String res = miniTaskActions.viewTaskDetail(getTaskId()).asString();
        taskResponse = JsonPath.with(res).getObject("data", TaskResponse.class);
    }

    @When("I get the task list")
    public void i_get_the_task_list() {
        task_ids = miniTaskActions.getTaskList();
    }

    @When("I delete the task")
    public void i_delete_the_task() {
        miniTaskActions.deleteTask(getTaskId());
    }

    @When("I delete all tasks")
    public void i_delete_all_tasks() {
        for (String id : task_ids) {
            miniTaskActions.deleteTask(id);
        }
    }

    @And("Check the task title {string}")
    public void check_the_task_title(String expectant_title) {
        miniTaskVerify.verifyTitle(taskResponse.getTitle(), expectant_title);
    }

    @And("Check the task description {string}")
    public void check_the_task_description(String expectant_desc) {
        miniTaskVerify.verifyDescription(taskResponse.getDescription().getText(), expectant_desc);
    }

    @And("Check the task priority {int}")
    public void check_the_task_priority(int expectant_priority) {
        miniTaskVerify.verifyPriority(taskResponse.getPriority(), expectant_priority);
    }

    @And("Check the task status {int}")
    public void check_the_task_status(int expectant_status) {
        miniTaskVerify.verifyStatus(taskResponse.getStatus(), expectant_status);
    }

    @And("Check the task has attachments {string}")
    public void check_the_task_has_attachments(String expectant_fileName) {
        String[] arrFileName = Helper.splitStringToList(expectant_fileName);
        List<String> actual_fileName = taskResponse.getAttachment_files()
                .stream().map(AttachmentFileObject::getName)
                .collect(Collectors.toList());

        miniTaskVerify.verifyAtfs(actual_fileName, Arrays.asList(arrFileName));
        System.out.println("Expected file name: " + expectant_fileName + "  Actual file name: " + actual_fileName);
    }

    @And("Check the task due date")
    public void check_the_task_due_date() {
        miniTaskVerify.verifyDueDate(taskResponse.getDue_date(), taskObject.getDue_date());
    }

    @Then("Check the task has assignee from id")
    public void check_the_task_has_assignee_from_id() {
        List<Integer> actual_assigneeId = taskResponse.getAssignees()
                .stream()
                .map(TaskResponse.Assignees::getAssignee_id)
                .collect(Collectors.toList());

        miniTaskVerify.verifyAssignees(actual_assigneeId, taskObject.getAssignees());
        System.out.println("Actual Assignee: " + actual_assigneeId);
    }

    @And("Check the task has watcher from id")
    public void check_the_task_has_watcher_from_id() {
        List<Integer> actual_watcherId = taskResponse.getWatchers()
                .stream()
                .map(TaskResponse.Watchers::getWatcher_id)
                .collect(Collectors.toList());

        miniTaskVerify.verifyWatchers(actual_watcherId, taskObject.getWatchers());
        System.out.println("Actual Watcher: " + actual_watcherId);
    }

    //  ------------------------ PROJECT ------------------------   //
    ProjectObject projectObject = new ProjectObject();
    List<Integer> user_ids;
    List<String> thread_ids;
    List<String> department_ids;
    List<String> role_ids;
    String[] key;

    public static String getProjectId() {
        return Serenity.sessionVariableCalled("projectId");
    }

    @When("I add members {string}")
    public void i_add_members(String member_key) {
        user_ids = new ArrayList<>();
        key = Helper.splitStringToList(member_key);
        for (String k : key) {
            user_ids.addAll(searchActions.searchUserInWorkspace(k));
        }
    }

    @When("I add threads {string}")
    public void i_add_threads(String thread_key) {
        thread_ids = new ArrayList<>();
        key = Helper.splitStringToList(thread_key);
        for (String k : key) {
            thread_ids.addAll(searchActions.searchChatConversation(k));
        }
    }

    @When("I add departments {string}")
    public void i_add_departments(String department_key) {
        department_ids = new ArrayList<>();
        key = Helper.splitStringToList(department_key);
        for (String k : key) {
            department_ids.addAll(searchActions.searchDepartment(k));
        }
    }

    @When("I add roles {string}")
    public void i_add_roles(String role_key) {
        role_ids = new ArrayList<>();
        key = Helper.splitStringToList(role_key);
        for (String k : key) {
            role_ids.addAll(searchActions.searchRole(k));
        }
    }

    @When("I create a project with just name {string}")
    public void i_create_a_project_with_just_name(String project_name) {
        projectObject = new ProjectObject(project_name);
        miniTaskActions.createProject(projectObject);

        Serenity.setSessionVariable("projectId").to(lastResponse().body().path("data.id"));
    }

    @When("I create a project with members {string}, {string}")
    public void i_create_a_project_with_members(String project_name, String member_type) {
        switch (member_type) {
            case "member":
                projectObject = new ProjectObject(project_name, user_ids, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
                miniTaskActions.createProject(projectObject);
                break;
            case "thread":
                projectObject = new ProjectObject(project_name, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), thread_ids);
                miniTaskActions.createProject(projectObject);
                break;
            case "department":
                projectObject = new ProjectObject(project_name, Collections.emptyList(), department_ids, Collections.emptyList(), Collections.emptyList());
                miniTaskActions.createProject(projectObject);
                break;
            case "role":
                projectObject = new ProjectObject(project_name, Collections.emptyList(), Collections.emptyList(), role_ids, Collections.emptyList());
                miniTaskActions.createProject(projectObject);
                break;
            default:
                projectObject = new ProjectObject(project_name, user_ids, department_ids, role_ids, thread_ids);
                miniTaskActions.createProject(projectObject);
                break;
        }

        Serenity.setSessionVariable("projectId").to(lastResponse().body().path("data.id"));
    }

    @When("I get the project info")
    public void i_get_the_project_info() {
        miniTaskActions.getProjectInfo(getProjectId());
    }

    @When("I edit the project name {string}")
    public void i_edit_the_project_name(String name_edit) {
        projectObject = new ProjectObject(name_edit);
        miniTaskActions.editProject(projectObject, getProjectId());

        Serenity.setSessionVariable("projectId").to(lastResponse().body().path("data.id"));
    }

    @Then("Check the project name {string}")
    public void check_the_project_name(String name) {
        miniTaskVerify.verifyProjectName(name);
    }

    @And("Check the project has members")
    public void check_the_project_has_members() {
        restAssuredThat(response -> response.body("data.members", hasSize(greaterThan(1))));
    }

    @When("I delete the project")
    public void i_delete_the_project() {
        miniTaskActions.deleteProject(getProjectId());
    }

    //  ------------------------ FOLDER ------------------------   //
    public static String getFolderId () { return Serenity.sessionVariableCalled("folderId").toString(); }
    public static String getDuplicatedFolderId () { return Serenity.sessionVariableCalled("duplicatedFolderId").toString(); }

    @When("I create a folder {string}")
    public void i_create_a_folder(String folder_name) {
        miniTaskActions.createFolder(folder_name, getProjectId());

        Serenity.setSessionVariable("folderId").to(lastResponse().body().path("data.id"));
    }

    @When("I edit the folder name {string}")
    public void i_edit_the_folder_name(String folderName_edit) {
        miniTaskActions.editFolder(folderName_edit, getFolderId());
    }

    @When("I duplicate the folder")
    public void i_duplicate_the_folder() {
        miniTaskActions.duplicateFolder(getFolderId());

        Serenity.setSessionVariable("duplicatedFolderId").to(lastResponse().body().path("data.id"));
    }

    @When("I delete the folder")
    public void i_delete_the_folder() {
        miniTaskActions.deleteFolder(getDuplicatedFolderId());
    }

    @And("Check the folder name {string}")
    public void check_the_folder_name(String expectant_folderName) {
        miniTaskVerify.verifyFolderName(expectant_folderName);
    }

    @And("Check the duplicated folder id")
    public void check_the_duplicated_folder_id() {
        restAssuredThat(response -> response.body("data.id", equalTo(getDuplicatedFolderId())));
    }
}
