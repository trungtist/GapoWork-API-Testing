package gapowork.pages.media;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

import static gapowork.constants.UrlConstants.UPLOAD_FILE_URL;
import static gapowork.pages.auth.AuthActions.access_token;

public class UploadActions {

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
}
