package gapowork.pages.auth;

import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class AuthActions {

    public static String access_token;
    public static int user_id;

    @Step("Check existent email")
    public String checkEmail(String email) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", email);

        Response res = SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .post("https://api.gapowork.vn/auth/v3.0/check-email")
                .then()
                .extract().response();

        return res.path("data.salt");
    }

    @Step("Check existent phone number")
    public String checkPhoneNumber(String phone_number) {
        Map<String, Object> body = new HashMap<>();
        body.put("phone_number", phone_number);

        Response res = SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .post("https://api.gapowork.vn/auth/v3.0/check-phone-number")
                .then()
                .extract().response();
        return res.path("data.salt");
    }

    @Step("Login with email and password")
    public void loginWithEmailAndPassword(String email, String password) {
        String salt = checkEmail(email);

        String passwordSHA1 = DigestUtils.sha256Hex(password + salt);
        String passwordSHA = DigestUtils.sha256Hex(passwordSHA1 + salt);

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("device_id", "3ad97216-777c-4ade-9117-16eee8082c9a");
        body.put("client_id", "cuxlp0ugglm3krp1ab81");
        body.put("trusted_device", false);
        body.put("password", passwordSHA);

        Response res = SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .post("https://api.gapowork.vn/auth/v3.0/login")
                .then()
                .extract().response();

        user_id = res.path("data.user_id");
        access_token = res.path("data.access_token");
    }

    @Step("Login with email and password")
    public void loginWithPhoneNumberAndPassword(String phone_number, String password) {
        String salt = checkPhoneNumber(phone_number);

        String passwordSHA1 = DigestUtils.sha256Hex(password + salt);
        String passwordSHA = DigestUtils.sha256Hex(passwordSHA1 + salt);

        Map<String, Object> body = new HashMap<>();
        body.put("phone_number", phone_number);
        body.put("device_id", "3ad97216-777c-4ade-9117-16eee8082c9a");
        body.put("client_id", "cuxlp0ugglm3krp1ab81");
        body.put("trusted_device", false);
        body.put("password", passwordSHA);

        Response res = SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .when()
                .body(body)
                .post("https://api.gapowork.vn/auth/v3.0/login")
                .then()
                .extract().response();

        user_id = res.path("data.user_id");
        access_token = res.path("data.access_token");
    }
}
