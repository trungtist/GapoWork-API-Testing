package gapowork.pages.miniTask;

import gapowork.models.miniTask.MiniTaskObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static gapowork.constants.UrlConstants.*;
import static gapowork.helper.Helper.*;
import static gapowork.pages.auth.AuthActions.access_token;

public class MiniTaskActions {

    @Step("Create task")
    public void createTask (String workspace_id, MiniTaskObject body) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .body(body)
                .post(CREATE_TASK_URL);
    }

    @Step("Edit task")
    public void editTask (String workspace_id, Object body, String task_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskId", task_id)
                .when()
                .body(body)
                .patch(EDIT_TASK_URL);
    }

    @Step("Delete task")
    public void deleteTask(String workspace_id, String task_id) {
        SerenityRest.given().contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .pathParam("taskId", task_id)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .delete(DELETE_TASK_URL);

        shortWait();
    }

    @Step("View task detail")
    public Response viewTaskDetail (String task_id, String workspace_id) {
        shortWait();
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskId", task_id)
                .when()
                .get(VIEW_TASK_DETAIL_URL);
    }

    @Step("Get task list")
    public List<String> getTaskList (String workspace_id) {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .get(GET_TASK_LIST_URL);

        return res.path("data.id");
    }

//    @SneakyThrows
//    @Step("Get data after uploaded file")
//    public AttachmentFileObject dataUploadFile (String url) {
//        Response res = UploadActions.uploadFile(url);
//        FileObject fileObject = res.jsonPath().getObject("", FileObject.class);
//        AttachmentFileObject attachmentFileObject = new AttachmentFileObject();
//        PropertyUtils.copyProperties(attachmentFileObject, fileObject);
//
//        return attachmentFileObject;
//    }

}
