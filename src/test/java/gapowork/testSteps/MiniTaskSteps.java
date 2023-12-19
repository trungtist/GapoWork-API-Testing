package gapowork.testSteps;

import gapowork.models.media.AttachmentFileObject;
import gapowork.models.miniTask.MiniTaskObject;
import gapowork.models.miniTask.MiniTaskResponse;
import gapowork.pages.miniTask.MiniTaskActions;
import gapowork.pages.miniTask.MiniTaskVerify;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;

import java.util.*;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;


public class MiniTaskSteps {
    @Steps
    MiniTaskActions miniTaskActions = new MiniTaskActions();
    MiniTaskVerify miniTaskVerify = new MiniTaskVerify();
    MiniTaskObject miniTaskObject = new MiniTaskObject();
    MiniTaskResponse miniTaskResponse = new MiniTaskResponse();
//    private List<Integer> assignees = new ArrayList<>();
//    private List<Integer> watchers = new ArrayList<>();
//    private List<AttachmentFileObject> attachment_files = new ArrayList<>();
//    private long due_date;
//    private int status;
//    private int priority;
//    private String description;

    public static String getTaskId() {
        return Serenity.sessionVariableCalled("taskId").toString();
    }

    @When("Enter the description {string}")
    public void enter_the_description(String desc) { miniTaskObject.setDescription(desc); }

    @And("I create a task {string}, {string}")
    public void i_create_a_task(String title, String workspace_id) {
        MiniTaskObject miniTaskBody = new MiniTaskObject(title, miniTaskObject.getDescription(), miniTaskObject.getAssignees(), miniTaskObject.getWatchers(), miniTaskObject.getDue_date(), miniTaskObject.getStatus(), miniTaskObject.getPriority(), miniTaskObject.getAttachment_files());
        miniTaskActions.createTask(workspace_id, miniTaskBody);

        Serenity.setSessionVariable("taskId").to(lastResponse().body().path("data.id"));
    }

    @When("I edit the task title {string}, {string}")
    public void i_edit_the_task_title(String workspace_id, String edited_title) {
        miniTaskObject.setTitle(edited_title);
        miniTaskActions.editTask(workspace_id, miniTaskObject, getTaskId());
    }

    @And("I edit the task description {string}, {string}")
    public void i_edit_the_task_description(String workspace_id, String edited_description) {
        miniTaskObject.setDescription(edited_description);
        miniTaskActions.editTask(workspace_id, miniTaskObject, getTaskId());
    }

    @When("I want to view the task detail {string}")
    public void i_want_to_view_the_task_detail(String workspace_id) {
        String res = miniTaskActions.viewTaskDetail(getTaskId(), workspace_id).asString();
        // miniTaskResponse = JsonPath.with(res).getObject("data", MiniTaskResponse.class);
    }

    @When("I delete the task {string}")
    public void i_delete_the_task(String workspace_id) {
        miniTaskActions.deleteTask(workspace_id, getTaskId());
    }

    @Then("Check success status")
    public void check_success_status() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Then("Check failure status")
    public void check__failure_status() { restAssuredThat(response -> response.statusCode(400)); }

    @And("Check the task title {string}")
    public void check_the_task_title(String expectantTitle) {
        miniTaskVerify.verifyTitle(miniTaskResponse.getTitle(), expectantTitle);
    }

    @And("Check the task description {string}")
    public void check_the_task_description(String expectantDesc) {
        miniTaskVerify.verifyDescription(miniTaskResponse.getDescription(), expectantDesc);
    }

}
