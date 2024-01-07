package gapowork.pages.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gapowork.models.media.ImageObject;
import gapowork.models.media.VideoObject;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

import static gapowork.constants.UrlConstants.*;
import static gapowork.hook.Base.workspace_id;
import static gapowork.pages.auth.AuthActions.access_token;

public class UploadActions {
    @Step("Upload file")
    public static Response uploadFile(String fileUrl) {
        File file = new File(fileUrl);
        return SerenityRest
                .given()
                .auth().oauth2(access_token)
                .when()
                .multiPart("file", file)
                .post(UPLOAD_FILE_URL)
                .then()
                .extract().response();
    }

    @SneakyThrows
    @Step("Upload image")
    public static ImageObject uploadImage(String imageUrl) {
        File image = new File(imageUrl);
        Response res = SerenityRest
                .given()
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .multiPart("image", image)
                .post(UPLOAD_IMAGE_URL)
                .then()
                .extract().response();

        return new ObjectMapper().readValue(res.prettyPrint(), new TypeReference<ImageObject>() {
        });
    }

    @SneakyThrows
    @Step("Upload video")
    public static VideoObject uploadVideo(String videoUrl) {
        File video = new File(videoUrl);
        Response res = SerenityRest
                .given()
                .auth().oauth2(access_token)
                .header("x-gapo-workspace-id", workspace_id)
                .when()
                .multiPart("video", video)
                .post(UPLOAD_VIDEO_URL)
                .then()
                .extract().response();

        return new ObjectMapper().readValue(res.prettyPrint(), new TypeReference<VideoObject>() {
        });
    }
}
