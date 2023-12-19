package gapowork.pages.miniTask;

import net.serenitybdd.annotations.Step;
import org.assertj.core.api.Assertions;

import java.util.List;

public class MiniTaskVerify {

    @Step
    public MiniTaskVerify verifyTitle(String actualTitle, String expectantTitle) {
        Assertions.assertThat(actualTitle).isEqualTo(expectantTitle);
        return this;
    }

    @Step
    public MiniTaskVerify verifyDescription(String actualDesc, String expectantDesc) {
        Assertions.assertThat(actualDesc).isEqualTo(expectantDesc);
        return this;
    }

    @Step
    public MiniTaskVerify verifyAssignees(List<Integer> actualAssignees, List<Integer> expectantAssignees) {
        Assertions.assertThat(actualAssignees).isEqualTo(expectantAssignees);
        return this;
    }

    @Step
    public MiniTaskVerify verifyWatchers(List<Integer> actualWatchers, List<Integer> expectantWatchers) {
        Assertions.assertThat(actualWatchers).isEqualTo(expectantWatchers);
        return this;
    }

    @Step
    public MiniTaskVerify verifyDueDate(long actualDueDate, long expectantDueDate) {
        Assertions.assertThat(actualDueDate).isEqualTo(expectantDueDate);
        return this;
    }

    @Step
    public MiniTaskVerify verifyStatus(int actualStatus, int expectantStatus) {
        Assertions.assertThat(actualStatus).isEqualTo(expectantStatus);
        return this;
    }

    @Step
    public MiniTaskVerify verifyPriority(int actualPriority, int expectantPriority) {
        Assertions.assertThat(actualPriority).isEqualTo(expectantPriority);
        return this;
    }

    @Step
    public MiniTaskVerify verifyAtfs(List<Integer> actualAtfs, List<Integer> expectantAtf) {
        Assertions.assertThat(actualAtfs).isEqualTo(expectantAtf);
        return this;
    }
}
