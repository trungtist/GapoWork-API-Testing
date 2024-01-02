package gapowork.testSteps;

import gapowork.helper.Helper;
import gapowork.models.media.AttachmentFileObject;
import gapowork.models.media.FileObject;
import gapowork.models.miniTask.MiniTaskObject;
import gapowork.models.miniTask.MiniTaskResponse;
import gapowork.pages.media.UploadActions;
import gapowork.pages.miniTask.MiniTaskActions;
import gapowork.pages.miniTask.MiniTaskVerify;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import org.apache.commons.beanutils.PropertyUtils;

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
    private List<AttachmentFileObject> attachmentFileObjectList = new ArrayList<>();

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

    @And("I create a task {string}, {string}")
    public void i_create_a_task(String title, String workspace_id) {
        MiniTaskObject miniTaskBody = new MiniTaskObject(title,
                miniTaskObject.getDescription(),
                miniTaskObject.getAssignees(),
                miniTaskObject.getWatchers(),
                miniTaskObject.getDue_date(),
                miniTaskObject.getStatus(),
                miniTaskObject.getPriority(),
                attachmentFileObjectList);
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

    @When("I edit the task priority {string}, {int}")
    public void i_edit_the_task_priority(String workspace_id, int edited_priority) {
        miniTaskObject.setPriority(edited_priority);
        miniTaskActions.editTask(workspace_id, miniTaskObject, getTaskId());
    }

    @And("I edit the task status {string}, {int}")
    public void i_edit_the_task_status(String workspace_id, int edited_status) {
        miniTaskObject.setStatus(edited_status);
        miniTaskActions.editTask(workspace_id, miniTaskObject, getTaskId());
    }

    @When("I continue to add attachment {string}, {string}")
    public void i_continue_to_add_attachment(String new_url, String workspace_id) {
        String[] arrFileUrl = Helper.splitStringToList(new_url);
        for (String s : arrFileUrl) {
            Response res = UploadActions.uploadFile(s);
            attachmentFileObjectList.add(res.jsonPath().getObject("", AttachmentFileObject.class));
        }

        miniTaskObject.setAttachment_files(attachmentFileObjectList);
        miniTaskActions.editTask(workspace_id, miniTaskObject, getTaskId());
    }

    @When("I want to view the task detail {string}")
    public void i_want_to_view_the_task_detail(String workspace_id) {
        String res = miniTaskActions.viewTaskDetail(getTaskId(), workspace_id).asString();
        miniTaskResponse = JsonPath.with(res).getObject("data", MiniTaskResponse.class);
    }

    @When("I delete the task {string}")
    public void i_delete_the_task(String workspace_id) {
        miniTaskActions.deleteTask(workspace_id, getTaskId());
    }

    @And("Check the task title {string}")
    public void check_the_task_title(String expectantTitle) {
        miniTaskVerify.verifyTitle(miniTaskResponse.getTitle(), expectantTitle);
    }

    @And("Check the task description {string}")
    public void check_the_task_description(String expectantDesc) {
        miniTaskVerify.verifyDescription(miniTaskResponse.getDescription().getText(), expectantDesc);
    }

    @And("Check the task priority {int}")
    public void check_the_task_priority(int expectantPriority) {
        miniTaskVerify.verifyPriority(miniTaskResponse.getPriority(), expectantPriority);
    }

    @And("Check the task status {int}")
    public void check_the_task_status(int expectantStatus) {
        miniTaskVerify.verifyStatus(miniTaskResponse.getStatus(), expectantStatus);
    }

    @And("Check the task has attachments {string}")
    public void check_the_task_has_attachments(String expectantFileName) {
        String[] arrFileName = Helper.splitStringToList(expectantFileName);
        List<String> actualFileName = miniTaskResponse.getAttachment_files()
                .stream().map(AttachmentFileObject::getName).collect(Collectors.toList());

        miniTaskVerify.verifyAtfs(actualFileName, Arrays.asList(arrFileName));
        System.out.println("Expected file name: " + expectantFileName + "  Actual file name: " + actualFileName);
    }

}
