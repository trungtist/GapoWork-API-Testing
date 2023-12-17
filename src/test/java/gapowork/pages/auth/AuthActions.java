package gapowork.pages;

import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.Map;

public class AuthActions {

    @Step("Check existent email")
    public void checkEmail(String email) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);

        SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .post("https://api.gapowork.vn/auth/v3.0/check-email")
                .then()
                .extract().response().prettyPrint();
    }

}
