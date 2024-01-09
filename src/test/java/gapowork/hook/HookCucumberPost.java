package gapowork.hook;

import gapowork.pages.auth.AuthActions;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

import java.awt.*;

import static gapowork.constants.AccountConstants.EMAIL;
import static gapowork.constants.AccountConstants.PASSWORD;

public class HookCucumberPost extends Base{
    private static boolean beforeFeature = false;

    @Steps
    AuthActions authActions = new AuthActions();
    EnvironmentVariables env;

    @Before("@post")
    public void user_login_on_gapowork(Scenario scenario) throws InterruptedException, AWTException {
        RestAssured.baseURI = EnvironmentSpecificConfiguration.from(env).getProperty("base.url");

        if (!beforeFeature) {
            super.CallEnvValue();
            authActions.loginWithEmailAndPassword(EMAIL, PASSWORD);
            beforeFeature = true;
            System.out.println("BEFORE HOOK MINI TASK");
        }
    }
}