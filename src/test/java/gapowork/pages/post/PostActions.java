package gapowork.pages.post;

import gapowork.models.post.PostObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static gapowork.constants.UrlConstants.*;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class PostActions {

    @Step("Create post")
    public void createPost (PostObject body) {
        Response res = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .body(body)
                .post(CREATE_POST_URL);

    }
}
