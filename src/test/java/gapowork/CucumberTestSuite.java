package gapowork;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class) // @RunWith(CucumberWithSerenity.class) is a JUnit annotation used in Serenity BDD to specify the test runner class for running Cucumber tests with Serenity.
@CucumberOptions(
    plugin = {"pretty"},
    features = "classpath:features"
)
public class CucumberTestSuite {

}
