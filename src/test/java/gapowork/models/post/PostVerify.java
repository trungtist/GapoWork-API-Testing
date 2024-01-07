package gapowork.models.post;

import net.serenitybdd.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostVerify {
    @Step("Verify content")
    public void verifyContent (String content) {
        restAssuredThat(response -> response.body("data.content", equalTo(content)));
    }

    @Step("Verify message")
    public void verifyMessage (String message) {
        restAssuredThat(response -> response.body("message", equalTo(message)));
    }

    @Step("Verify success status")
    public void verifySuccessStatus (int successStatus) {
        restAssuredThat(response -> response.statusCode(successStatus));
    }

    @Step("Verify created status")
    public void verifyCreatedStatus (int createdStatus) {
        restAssuredThat(response -> response.statusCode(createdStatus));
    }

}
