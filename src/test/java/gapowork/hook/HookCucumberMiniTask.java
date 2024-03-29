package gapowork.hook;

import gapowork.pages.auth.AuthActions;
import gapowork.pages.miniTask.MiniTaskActions;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

import java.awt.*;

import static gapowork.constants.AccountConstants.*;

public class HookCucumberMiniTask extends Base {
    private static boolean beforeFeature = false;

    @Steps
    AuthActions authActions = new AuthActions();
    MiniTaskActions miniTaskActions = new MiniTaskActions();
    EnvironmentVariables env;
    public static String project_id;
//    public static String workspace_id;


    @Before("@task or @project or @taskList")
    public void user_login_on_gapowork(Scenario scenario) throws InterruptedException, AWTException {
        RestAssured.baseURI = EnvironmentSpecificConfiguration.from(env).getProperty("base.url");

        if (!beforeFeature) {
            super.CallEnvValue();
//            workspace_id = EnvironmentSpecificConfiguration.from(env).getProperty("workspace.id");
            authActions.loginWithEmailAndPassword(EMAIL, PASSWORD);
            beforeFeature = true;
            project_id = miniTaskActions.getProjectIdFromProjectList();
            System.out.println("BEFORE HOOK MINI TASK");
        }
    }
}
