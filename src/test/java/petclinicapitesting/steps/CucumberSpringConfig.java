package petclinicapitesting.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import petclinicapitesting.springconfig.Config;

@ContextConfiguration(classes = Config.class)
@CucumberContextConfiguration
public class CucumberSpringConfig {
}