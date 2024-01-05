package gapowork.testSteps;

import gapowork.models.post.PostObject;
import gapowork.pages.post.PostActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import static gapowork.pages.auth.AuthActions.user_id;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class PostSteps {

    @Steps
    PostActions postActions = new PostActions();

    @When("I create post with text content {string}, privacy {int} and target {string}")
    public void i_create_post_with_text_content_privacy_and_target(String content, Integer privacy, String target) {
        target = target + user_id;
        PostObject body = new PostObject(
            content, 0, privacy, target
        );
        postActions.createPost(body);
    }
    @Then("Check create successfully {int}")
    public void check_create_successfully(Integer create_status) {
        restAssuredThat(response -> response.statusCode(create_status));
    }
    @Then("Check message {string}")
    public void check_message(String message) {
        restAssuredThat(response -> response.body("message", equalTo(message)));
    }
    @When("I want to view the post detail")
    public void i_want_to_view_the_post_detail() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I update post {string}")
    public void i_update_post(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I delete the post")
    public void i_delete_the_post() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
