package gapowork.pages.miniTask;

import gapowork.models.miniTask.ProjectObject;
import gapowork.models.miniTask.TaskObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gapowork.constants.UrlConstants.*;
import static gapowork.helper.Helper.*;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class MiniTaskActions {

    // -------------------------- PROJECT -------------------------- //
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

    @Step("Get project list")
    public Response getProjectlist () {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .get(GET_PROJECT_LIST_URL);
    }

    @Step("Get project id from project list")
    public String getProjectIdFromProjectList () {
        Response res = getProjectlist();
        List<String> project_ids = res.jsonPath().getList("data.id");

        return project_ids.get(0);
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

    // -------------------------- TASK -------------------------- //
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


    // -------------------------- FOLDER -------------------------- //
    @Step("Create folder")
    public void createFolder(String folder_name, String project_id) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", folder_name);
        body.put("project_id", project_id);

        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .body(body)
                .post(CREATE_FOLDER_URL);
    }

    @Step("Edit folder")
    public void editFolder(String folder_name, String folder_id) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", folder_name);

        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("folderId", folder_id)
                .when()
                .body(body)
                .patch(EDIT_FOLDER_URL);
    }

    @Step("Duplicate folder")
    public void duplicateFolder(String folder_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("folderId", folder_id)
                .when()
                .post(DUPLICATE_FOLDER_URL);
    }

    @Step("Delete folder")
    public void deleteFolder(String folder_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("folderId", folder_id)
                .when()
                .delete(DELETE_FOLDER_URL);
    }

    // -------------------------- TASK LIST -------------------------- //
    @Step("Create task list")
    public void createTaskList(String taskList_name, String project_id, String folder_id) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", taskList_name);
        body.put("project_id", project_id);
        body.put("folder_id", folder_id);

        SerenityRest
                .given()
                .auth().oauth2(access_token)
                .contentType(ContentType.JSON)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .body(body)
                .post(CREATE_TASK_LIST_URL);
    }

    @Step("Edit task list")
    public void editTaskList(String taskList_id, String taskListName_edit, String description, String content_rtf) {
        Map<String, Object> body = new HashMap<>();
        body.put("name", taskListName_edit);
        body.put("description", description);
        body.put("content_rtf", Boolean.valueOf(content_rtf));

        SerenityRest
                .given()
                .auth().oauth2(access_token)
                .contentType(ContentType.JSON)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskListId", taskList_id)
                .when()
                .body(body)
                .patch(EDIT_TASK_LIST_URL);
    }

    @Step("Duplicate task list")
    public void duplicateTaskList(String taskList_id) {
        SerenityRest
                .given()
                .auth().oauth2(access_token)
                .contentType(ContentType.JSON)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskListId", taskList_id)
                .when()
                .post(DUPLICATE_TASK_LIST_URL);
    }

    @Step("Delete task list")
    public void deleteTaskList(String taskList_id) {
        SerenityRest
                .given()
                .auth().oauth2(access_token)
                .contentType(ContentType.JSON)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("taskListId", taskList_id)
                .when()
                .delete(DELETE_TASK_LIST_URL);
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
}
