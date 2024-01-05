package gapowork.pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static gapowork.constants.UrlConstants.SEARCH_USER_IN_WORKSPACE;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class SearchActions {

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
}
