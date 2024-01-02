package gapowork.pages.media;

import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

import static gapowork.constants.UrlConstants.UPLOAD_FILE_URL;
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
                .post("https://upload.gapowork.vn/media/v1.0/files")
                .then()
                .extract().response();
    }
}
