package gapowork.pages.miniTask;

import gapowork.models.miniTask.MiniTaskObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static gapowork.constants.UrlConstants.*;
import static gapowork.pages.auth.AuthActions.access_token;

public class MiniTaskActions {

    @Step("Create task")
    public void createTask (String workspace_id, MiniTaskObject body) {

        System.out.println("access_token" + access_token);

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
    public void editTask (String workspace_id, Object body, String task_Id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskId", task_Id)
                .when()
                .body(body)
                .patch(EDIT_TASK_URL);
    }

    @Step("Delete task")
    public void deleteTask(String workspace_id, String task_Id) {
        SerenityRest.given().contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .pathParam("taskId", task_Id)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .delete(DELETE_TASK_URL);
    }

    @Step("View task detail")
    public Response viewTaskDetail (String task_id, String workspace_id) {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskId", task_id)
                .when()
                .get(VIEW_TASK_DETAIL_URL);
    }


}
