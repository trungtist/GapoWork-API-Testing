package gapowork.pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static gapowork.constants.UrlConstants.*;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class SearchActions {

    @Step("Search user in workspace")
    public List<Integer> searchUserInWorkspace (String key) {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .queryParam("q", key)
                .queryParam("workspace_id", workspace_id)
                .queryParam("limit", 1)
                .when()
                .get(SEARCH_USER_IN_WORKSPACE);
        return res.path("data.id");
    }

    @Step("Search chat conversation")
    public List<String> searchChatConversation (String key) {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .queryParam("q", key)
                .queryParam("limit", 1)
                .when()
                .get(SEARCH_CHAT_CONVERSATION_URL);
        return res.path("data.id");
    }

    @Step("Search department")
    public List<String> searchDepartment (String key) {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .queryParam("q", key)
                .queryParam("limit", 1)
                .when()
                .get(SEARCH_DEPARTMENT_URL);
        return res.path("data.department_id");
    }

    @Step("Search role")
    public List<String> searchRole (String key) {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .queryParam("q", key)
                .queryParam("limit", 1)
                .when()
                .get(SEARCH_ROLE_URL);
        return res.path("data.role_id");
    }
}
