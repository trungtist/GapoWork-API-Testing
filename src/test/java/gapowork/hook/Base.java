package gapowork.hook;

import io.restassured.RestAssured;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

public class Base {
    EnvironmentVariables env;
    public static String workspace_id;

    public void CallEnvValue() {
        workspace_id = getProperty("workspace.id");
    }

    private String getProperty(String s) {
        return EnvironmentSpecificConfiguration.from(env).getProperty(s);
    }
}
