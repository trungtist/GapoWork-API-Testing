package gapowork.pages.post;

import gapowork.models.post.PostObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static gapowork.constants.UrlConstants.*;
import static gapowork.helper.Helper.shortWait;
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
        shortWait();
    }

    @Step("Update post")
    public void updatePost (PostObject body, String postId) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("id", postId)
                .when()
                .body(body)
                .put(UPDATE_POST_URL);
        shortWait();
    }

    @Step("Get post detail")
    public Response getPostDetail(String post_id) {
        return SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("id", post_id)
                .when()
                .get(GET_POST_DETAIL_URL);
    }

    @Step("Delete post")
    public void deletePost (String post_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("id", post_id)
                .when()
                .delete(DELETE_POST_URL);
    }
}
