package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features/Employee.feature",
		glue= "stepDefinations",strict=true, monochrome=true,
		plugin = {"pretty","html:target/cucumber","json:target/jsonReports/cucumber-report.json","junit:target/cukes.xml"}		)


public class TestRunner {

}
