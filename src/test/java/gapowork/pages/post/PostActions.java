package gapowork.pages.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gapowork.models.media.BackgroundObject;
import gapowork.models.post.PostObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static gapowork.constants.UrlConstants.*;
import static gapowork.helper.Helper.shortWait;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class PostActions {
    @Step("Create post")
    public void createPost(PostObject body) {
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
    public void updatePost(PostObject body, String postId) {
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
    public void deletePost(String post_id) {
        SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .pathParam("id", post_id)
                .when()
                .delete(DELETE_POST_URL);
    }

    @SneakyThrows
    @Step("Get background post list")
    public static Map<String, Object> getBackgroundList() {
        Response res = SerenityRest
                .given()
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .get(GET_BACKGROUND_POST_URL)
                .then()
                .extract().response();

        int res_length = res.path("data.size()");
        Random random = new Random();
        int rand_res = random.nextInt(res_length) + 1;

        Map<String, Object> background_object = new HashMap<>();
        background_object.put("id", res.path("data["+rand_res+"].id").toString());
        background_object.put("src", res.path("data["+rand_res+"].src").toString());
        background_object.put("width", res.path("data["+rand_res+"].width"));
        background_object.put("height", res.path("data["+rand_res+"].height"));
        background_object.put("font_color", res.path("data["+rand_res+"].font_color").toString());

        return background_object;
    }
}
