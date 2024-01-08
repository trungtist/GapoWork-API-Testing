package gapowork.testSteps;

import gapowork.models.media.ImageObject;
import gapowork.models.media.MediaObject;
import gapowork.models.media.VideoObject;
import gapowork.models.post.PostObject;
import gapowork.pages.post.PostVerify;
import gapowork.pages.group.GroupActions;
import gapowork.pages.media.UploadActions;
import gapowork.pages.post.PostActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class PostSteps {

    @Steps
    PostActions postActions = new PostActions();
    PostVerify postVerify = new PostVerify();
    PostObject postObject = new PostObject();
    GroupActions groupActions = new GroupActions();
    ImageObject imageObject = new ImageObject();
    VideoObject videoObject = new VideoObject();
    MediaObject mediaObject = new MediaObject();
    public List<MediaObject> mediaObjectList = new ArrayList<>();

    public static String getPostId() {
        return Serenity.sessionVariableCalled("id").toString();
    }

    public String getPostContent() {
        return Serenity.sessionVariableCalled("content").toString();
    }

    public int getPostPrivacy() {
        return Serenity.sessionVariableCalled("privacy");
    }

    @When("I create post with text content {string}, privacy {int} and target {string}")
    public void i_create_post_with_text_content_privacy_and_target(String content, Integer privacy, String target) {
        target = groupActions.checkTarget(target);
        postObject = new PostObject(
                content, 0, privacy, target
        );
        postActions.createPost(postObject);

        Serenity.setSessionVariable("id").to(lastResponse().body().path("data.id"));
        Serenity.setSessionVariable("content").to(content);
        Serenity.setSessionVariable("privacy").to(privacy);

    }

    @SneakyThrows
    @When("I create post with medias {string}, {string}, {string}, privacy {int} and target {string}")
    public void i_create_post_with_medias_privacy_and_target(String content, String type, String file_name, Integer privacy, String target) {
        target = groupActions.checkTarget(target);
        if (type.equals("image")) {
            imageObject = UploadActions.uploadImage("src/test/resources/datas/" + file_name);
            if (content.equals("Invalid media")) {
                PropertyUtils.copyProperties(mediaObject, imageObject);
                mediaObject.setHeight(null);
            } else PropertyUtils.copyProperties(mediaObject, imageObject);
        } else {
            videoObject = UploadActions.uploadVideo("src/test/resources/datas/" + file_name);
            mediaObject.setType(type);
            mediaObject.setSrc(videoObject.getFile_link());
            mediaObject.setId(videoObject.getId());
        }

        mediaObjectList.add(mediaObject);
        postObject = new PostObject(content, Collections.singletonList(mediaObject), privacy, target);
        postActions.createPost(postObject);

        Serenity.setSessionVariable("id").to(lastResponse().body().path("data.id"));
    }

    @SneakyThrows
    @When("I create post with background {string}, {string}, privacy {int} and target {string}")
    public void i_create_post_with_background_privacy_and_target(String content, String type, Integer privacy, String target) {
        target = groupActions.checkTarget(target);
        if (type.equals("background")) {
            if (content.equals("Invalid background")) {
                PropertyUtils.copyProperties(mediaObject, PostActions.getBackgroundList());
                mediaObject.setType(type);
                mediaObject.setSrc(null);
            } else {
                PropertyUtils.copyProperties(mediaObject, PostActions.getBackgroundList());
                mediaObject.setType(type);
            }
        }

        mediaObjectList.add(mediaObject);
        postObject = new PostObject(content, Collections.singletonList(mediaObject), privacy, target);
        postActions.createPost(postObject);

        Serenity.setSessionVariable("id").to(lastResponse().body().path("data.id"));
        Serenity.setSessionVariable("privacy").to(privacy);
    }

    @When("I want to view the post detail")
    public void i_want_to_view_the_post_detail() {
        postActions.getPostDetail(getPostId());
    }

    @When("I update post {string}")
    public void i_update_post(String content_edit) {
        postObject = new PostObject(getPostId(), content_edit, Collections.emptyList(), getPostPrivacy());
        postActions.updatePost(postObject, getPostId());

        Serenity.setSessionVariable("content").to(content_edit);
    }

    @SneakyThrows
    @When("I update post with new background {string}, {string}")
    public void i_update_post_with_new_background(String content_edit, String type) {
        if (type.equals("background")) {
            PropertyUtils.copyProperties(mediaObject, PostActions.getBackgroundList());
            mediaObject.setType(type);
        }
        postObject = new PostObject(getPostId(), content_edit, Collections.singletonList(mediaObject), getPostPrivacy());
        postActions.updatePost(postObject, getPostId());
    }

    @Then("Check create successfully {int}")
    public void check_create_successfully(Integer create_status) {
        postVerify.verifyCreatedStatus(create_status);
    }

    @Then("Check message {string}")
    public void check_message(String message) {
        postVerify.verifyMessage(message);
    }

    @And("Check the post text content")
    public void check_the_post_text_content() {
        postVerify.verifyContent(getPostContent());
    }

    @And("Check the background post")
    public void check_the_background_post() {
        String expectantSrc = SerenityRest.lastResponse().path("data.background.src");
        postVerify.verifyBackgroundPost(expectantSrc);
    }

    @When("I delete the post")
    public void i_delete_the_post() {
        postActions.deletePost(getPostId());
    }

    @Then("Check for incorrect precondition {int}")
    public void check_for_incorrect_precondition(Integer preconditionFailed_status) {
        restAssuredThat(response -> response.statusCode(preconditionFailed_status));
    }

    @When("Get bg")
    public void get_bg() {
        PostActions.getBackgroundList();
    }
}
