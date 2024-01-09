package gapowork.pages.miniTask;

import gapowork.models.miniTask.ProjectObject;
import gapowork.models.miniTask.TaskObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static gapowork.constants.UrlConstants.*;
import static gapowork.helper.Helper.*;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class MiniTaskActions {

    @Step("Create task")
    public void createTask(TaskObject body) {
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
    public void editTask(Object body, String task_id) {
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
    public void deleteTask(String task_id) {
        SerenityRest.given().contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .pathParam("taskId", task_id)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .delete(DELETE_TASK_URL);

        shortWait();
    }

    @Step("View task detail")
    public Response viewTaskDetail(String task_id) {
//        shortWait();
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
    public List<String> getTaskList() {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .get(GET_TASK_LIST_URL);

        return res.path("data.id");
    }

    @Step("Create project")
    public void createProject(ProjectObject body) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .body(body)
                .post(CREATE_PROJECT_URL);
    }

    @Step("Edit project")
    public void editProject(ProjectObject body, String project_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("projectId", project_id)
                .when()
                .body(body)
                .patch(EDIT_PROJECT_URL);
    }

    @Step("Get project info")
    public Response getProjectInfo(String project_id) {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("projectId", project_id)
                .when()
                .get(GET_PROJECT_INFO_URL);
    }

    @Step("Delete project")
    public void deleteProject(String project_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("projectId", project_id)
                .when()
                .delete(DELETE_PROJECT_URL);
    }

}
