package gapowork.testSteps;

import gapowork.pages.miniTask.MiniTaskActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class MiniTaskSteps {

    @Steps
    MiniTaskActions miniTaskActions = new MiniTaskActions();

    @When("I create a task {string}, {string}")
    public void i_create_a_task(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Enter the description {string}")
    public void enter_the_description(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Check the task title {string}")
    public void check_the_task_title(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Check the task description {string}")
    public void check_the_task_description(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I edit the task title {string}, {string}")
    public void i_edit_the_task_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I edit the task description {string}, {string}")
    public void i_edit_the_task_description(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I want to view the task detail {string}")
    public void i_want_to_view_the_task_detail(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I delete the task {string}")
    public void i_delete_the_task(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
