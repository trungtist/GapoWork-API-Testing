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

    @Then("Check status successfully")
    public void check_status_successfully() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Then("Check for invalid status")
    public void check_for_invalid_status() {
        restAssuredThat(response -> response.statusCode(400));
    }

//    @Then("I get access token")
//    public void i_get_access_token() {
//        throw new io.cucumber.java.PendingException();
//    }
}
