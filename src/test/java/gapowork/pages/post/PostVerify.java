package gapowork.pages.post;

import gapowork.models.media.MediaObject;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostVerify {
    @Steps
    MediaObject mediaObject = new MediaObject();

    @Step("Verify content")
    public void verifyContent(String content) {
        restAssuredThat(response -> response.body("data.content", equalTo(content)));
    }

    @Step("Verify message")
    public void verifyMessage(String message) {
        restAssuredThat(response -> response.body("message", equalTo(message)));
    }

    @Step("Verify media src")
    public void verifyBackgroundPost(String src) {
        restAssuredThat(response -> response.body("data.background.src", equalTo(src)));
    }

    @Step("Verify success status")
    public void verifySuccessStatus(int successStatus) {
        restAssuredThat(response -> response.statusCode(successStatus));
    }

    @Step("Verify created status")
    public void verifyCreatedStatus(int createdStatus) {
        restAssuredThat(response -> response.statusCode(createdStatus));
    }


}
