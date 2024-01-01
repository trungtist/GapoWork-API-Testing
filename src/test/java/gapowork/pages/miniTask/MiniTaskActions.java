package gapowork.pages.miniTask;

import gapowork.models.media.AttachmentFileObject;
import gapowork.models.media.FileObject;
import gapowork.models.miniTask.MiniTaskObject;
import gapowork.pages.media.UploadActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.beanutils.PropertyUtils;

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

    @SneakyThrows
    @Step("Get file data")
    public void getFileData(String url) {
        Response res = UploadActions.uploadFile(url);
    }

}
