package gapowork.pages.miniTask;

import net.serenitybdd.annotations.Step;
import org.assertj.core.api.Assertions;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class MiniTaskVerify {

    @Step("Verify title")
    public MiniTaskVerify verifyTitle(String actualTitle, String expectantTitle) {
        Assertions.assertThat(actualTitle).isEqualTo(expectantTitle);
        return this;
    }

    @Step("Verify description")
    public MiniTaskVerify verifyDescription(String actualDesc, String expectantDesc) {
        Assertions.assertThat(actualDesc).isEqualTo(expectantDesc);
        return this;
    }

    @Step("Verify assignee list")
    public MiniTaskVerify verifyAssignees(List<Integer> actualAssignees, List<Integer> expectantAssignees) {
        Assertions.assertThat(actualAssignees).isEqualTo(expectantAssignees);
        return this;
    }

    @Step("Verify watcher list")
    public MiniTaskVerify verifyWatchers(List<Integer> actualWatchers, List<Integer> expectantWatchers) {
        Assertions.assertThat(actualWatchers).isEqualTo(expectantWatchers);
        return this;
    }

    @Step("Verify due date")
    public MiniTaskVerify verifyDueDate(Long actualDueDate, Long expectantDueDate) {
        Assertions.assertThat(actualDueDate).isEqualTo(expectantDueDate);
        return this;
    }

    @Step("Verify status")
    public MiniTaskVerify verifyStatus(int actualStatus, int expectantStatus) {
        Assertions.assertThat(actualStatus).isEqualTo(expectantStatus);
        return this;
    }

    @Step("Verify priority")
    public MiniTaskVerify verifyPriority(int actualPriority, int expectantPriority) {
        Assertions.assertThat(actualPriority).isEqualTo(expectantPriority);
        return this;
    }

    @Step("Verify atf")
    public MiniTaskVerify verifyAtfs(List<String> actualAtfs, List<String> expectantAtf) {
        Assertions.assertThat(actualAtfs).isEqualTo(expectantAtf);
        return this;
    }

    @Step("Verify project name")
    public void verifyProjectName (String expectantProjectName) {
        restAssuredThat(response -> response.body("data.name", equalTo(expectantProjectName)));
    }

    @Step("Verify folder name")
    public void verifyFolderName (String expectantFolderName) {
        restAssuredThat(response -> response.body("data.name", equalTo(expectantFolderName)));
    }

}
