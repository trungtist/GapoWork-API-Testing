package gapowork.testSteps;

import gapowork.pages.auth.AuthActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AuthSteps {
    @Steps
    AuthActions authActions = new AuthActions();

    @When("I want to check this email {string}")
    public void i_want_to_check_this_email(String email) {
        authActions.checkEmail(email);
    }

    @When("I want to check this phone number {string}")
    public void i_want_to_check_this_phone_number(String phone_number) {
        authActions.checkPhoneNumber(phone_number);
    }

    @When("I login with {string} and {string}")
    public void i_login_with_and(String email, String password) {
        authActions.loginWithEmailAndPassword(email, password);
    }

    @When("I login with {string}, {string}")
    public void i_login_with(String phone_number, String password) {
        authActions.loginWithPhoneNumberAndPassword(phone_number, password);
    }

    @Then("Check status successfully {int}")
    public void check_status_successfully(int successStatus) {
        restAssuredThat(response -> response.statusCode(successStatus));
    }

    @Then("Check for invalid status {int}")
    public void check_for_invalid_status(int failStatus) {
        restAssuredThat(response -> response.statusCode(failStatus));
    }
}
