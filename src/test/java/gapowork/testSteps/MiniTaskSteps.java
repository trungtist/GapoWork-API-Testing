package gapowork.testSteps;

import gapowork.models.media.AttachmentFileObject;
import gapowork.models.miniTask.MiniTaskObject;
import gapowork.pages.miniTask.MiniTaskActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;

import java.util.*;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class MiniTaskSteps {

    @Steps
    MiniTaskActions miniTaskActions = new MiniTaskActions();
    MiniTaskObject miniTaskObjectEdit = new MiniTaskObject();
    public List<Integer> assignees = new ArrayList<>();
    public List<Integer> watchers = new ArrayList<>();
    public List<AttachmentFileObject> attachment_files = new ArrayList<>();
    public long due_date;
    public int status;
    public int priority;
    public String description;

    public static String getTaskId() {
        return Serenity.sessionVariableCalled("taskId").toString();
    }

    @When("I create a task {string}, {string}")
    public void i_create_a_task(String title, String workspace_id) {
        MiniTaskObject miniTaskBody = new MiniTaskObject(title, description, assignees, watchers, due_date, status, priority, attachment_files);
        miniTaskActions.createTask(workspace_id, miniTaskBody);

        Serenity.setSessionVariable("taskId").to(lastResponse().body().path("data.id"));
    }

    @When("Enter the description {string}")
    public void enter_the_description(String desc) { description = desc; }

    @When("I edit the task title {string}, {string}")
    public void i_edit_the_task_title(String workspace_id, String edited_title) {
        miniTaskObjectEdit.title = edited_title;
        miniTaskActions.editTask(workspace_id, miniTaskObjectEdit, getTaskId());
    }

    @When("I edit the task description {string}, {string}")
    public void i_edit_the_task_description(String workspace_id, String edited_description) {
        miniTaskObjectEdit.description = edited_description;
        miniTaskActions.editTask(workspace_id, miniTaskObjectEdit, getTaskId());
    }

    @When("I want to view the task detail {string}")
    public void i_want_to_view_the_task_detail(String workspace_id) {

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

    @Then("Check the task title {string}")
    public void check_the_task_title(String string) {

    }

    @Then("Check the task description {string}")
    public void check_the_task_description(String string) {

    }

}
