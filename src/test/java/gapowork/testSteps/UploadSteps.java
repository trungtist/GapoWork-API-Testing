package gapowork.testSteps;

import gapowork.models.media.ImageObject;
import gapowork.models.media.VideoObject;
import gapowork.pages.media.UploadActions;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class UploadSteps {
    @When("I upload media {string} {string}")
    public void i_upload_media(String file_name, String type) {
        ImageObject imageObject = new ImageObject();
        VideoObject videoObject = new VideoObject();
        if(type.equals("image")) {
            imageObject = UploadActions.uploadImage(file_name);
        } else {
            videoObject = UploadActions.uploadVideo(file_name);
        }
    }
}
