package gapowork.testSteps;

import gapowork.helper.Helper;
import gapowork.models.media.AttachmentFileObject;
import gapowork.models.miniTask.MiniTaskObject;
import gapowork.models.miniTask.MiniTaskResponse;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.rest.SerenityRest.lastResponse;


public class MiniTaskSteps {
    @Steps
    MiniTaskActions miniTaskActions = new MiniTaskActions();
    MiniTaskObject miniTaskObject = new MiniTaskObject();
    MiniTaskResponse miniTaskResponse = new MiniTaskResponse();
    MiniTaskVerify miniTaskVerify = new MiniTaskVerify();
    SearchActions searchActions = new SearchActions();
    private List<AttachmentFileObject> attachmentFileObjectList = new ArrayList<>();
    List<String> task_ids;

    public static String getTaskId() {
        return Serenity.sessionVariableCalled("taskId").toString();
    }

    @When("Enter the description {string}")
    public void enter_the_description(String desc) {
        miniTaskObject.setDescription(desc);
    }

    @When("Enter the priority {int}")
    public void enter_the_priority(int priority) {
        miniTaskObject.setPriority(priority);
    }

    @And("Enter the status {int}")
    public void enter_the_status(int status) {
        miniTaskObject.setStatus(status);
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
        miniTaskObject.setDue_date(current_dueDate);
    }

    @When("I add a assignee {string}")
    public void i_add_a_assignee(String assignee_key) {
        miniTaskObject.setAssignees(searchActions.searchUserInWorkspace(assignee_key));
    }
    @And("I add a watcher {string}, {string}")
    public void i_add_a_watcher(String watcher_key) {
        miniTaskObject.setWatchers(searchActions.searchUserInWorkspace(watcher_key));
    }

    @And("I create a task {string}")
    public void i_create_a_task(String title) {
        MiniTaskObject miniTaskBody = new MiniTaskObject(title,
                miniTaskObject.getDescription(),
                miniTaskObject.getAssignees(),
                miniTaskObject.getWatchers(),
                miniTaskObject.getDue_date(),
                miniTaskObject.getStatus(),
                miniTaskObject.getPriority(),
                attachmentFileObjectList);
        miniTaskActions.createTask(miniTaskBody);

        Serenity.setSessionVariable("taskId").to(lastResponse().body().path("data.id"));
    }

    @When("I edit the task title {string}")
    public void i_edit_the_task_title(String edited_title) {
        miniTaskObject.setTitle(edited_title);
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @And("I edit the task description {string}")
    public void i_edit_the_task_description(String edited_description) {
        miniTaskObject.setDescription(edited_description);
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @When("I edit the task priority {int}")
    public void i_edit_the_task_priority(int edited_priority) {
        miniTaskObject.setPriority(edited_priority);
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @And("I edit the task status {int}")
    public void i_edit_the_task_status(int edited_status) {
        miniTaskObject.setStatus(edited_status);
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @When("I continue to add attachment {string}")
    public void i_continue_to_add_attachment(String new_url) {
        String[] arrFileUrl = Helper.splitStringToList(new_url);
        for (String s : arrFileUrl) {
            Response res = UploadActions.uploadFile(s);
            attachmentFileObjectList.add(res.jsonPath().getObject("", AttachmentFileObject.class));
        }

        miniTaskObject.setAttachment_files(attachmentFileObjectList);
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @When("I edit the task due date")
    public void i_edit_the_task_due_date() {
        long new_dueDate = Helper.getTimestamp() + 2;
        System.out.println("New due date: " + new_dueDate);
        miniTaskObject.setDue_date(new_dueDate);
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @When("I continue to add assignee {string}")
    public void i_continue_to_add_assignee(String assignee_newKey) {
        miniTaskObject.setAssignees(searchActions.searchUserInWorkspace(assignee_newKey));
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }
    @When("I continue to add watcher {string}")
    public void i_continue_to_add_watcher(String watcher_newKey) {
        miniTaskObject.setWatchers(searchActions.searchUserInWorkspace(watcher_newKey));
        miniTaskActions.editTask(miniTaskObject, getTaskId());
    }

    @When("I want to view the task detail")
    public void i_want_to_view_the_task_detail() {
        String res = miniTaskActions.viewTaskDetail(getTaskId()).asString();
        miniTaskResponse = JsonPath.with(res).getObject("data", MiniTaskResponse.class);
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
        miniTaskVerify.verifyTitle(miniTaskResponse.getTitle(), expectant_title);
    }

    @And("Check the task description {string}")
    public void check_the_task_description(String expectant_desc) {
        miniTaskVerify.verifyDescription(miniTaskResponse.getDescription().getText(), expectant_desc);
    }

    @And("Check the task priority {int}")
    public void check_the_task_priority(int expectant_priority) {
        miniTaskVerify.verifyPriority(miniTaskResponse.getPriority(), expectant_priority);
    }

    @And("Check the task status {int}")
    public void check_the_task_status(int expectant_status) {
        miniTaskVerify.verifyStatus(miniTaskResponse.getStatus(), expectant_status);
    }

    @And("Check the task has attachments {string}")
    public void check_the_task_has_attachments(String expectant_fileName) {
        String[] arrFileName = Helper.splitStringToList(expectant_fileName);
        List<String> actual_fileName = miniTaskResponse.getAttachment_files()
                .stream().map(AttachmentFileObject::getName)
                .collect(Collectors.toList());

        miniTaskVerify.verifyAtfs(actual_fileName, Arrays.asList(arrFileName));
        System.out.println("Expected file name: " + expectant_fileName + "  Actual file name: " + actual_fileName);
    }
    @And("Check the task due date")
    public void check_the_task_due_date() {
        miniTaskVerify.verifyDueDate(miniTaskResponse.getDue_date(), miniTaskObject.getDue_date());
    }

    @Then("Check the task has assignee from id")
    public void check_the_task_has_assignee_from_id() {
        List<Integer> actual_assigneeId = miniTaskResponse.getAssignees()
                .stream()
                .map(MiniTaskResponse.Assignees::getAssignee_id)
                .collect(Collectors.toList());

        miniTaskVerify.verifyAssignees(actual_assigneeId, miniTaskObject.getAssignees());
        System.out.println("Actual Assignee: " + actual_assigneeId);
    }

    @And("Check the task has watcher from id")
    public void check_the_task_has_watcher_from_id() {
        List<Integer> actual_watcherId = miniTaskResponse.getWatchers()
                .stream()
                .map(MiniTaskResponse.Watchers::getWatcher_id)
                .collect(Collectors.toList());

        miniTaskVerify.verifyWatchers(actual_watcherId, miniTaskObject.getWatchers());
        System.out.println("Actual Watcher: " + actual_watcherId);
    }

}
