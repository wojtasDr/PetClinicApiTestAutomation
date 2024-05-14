package petclinicapitesting.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@PropertySource("application.properties")
@ComponentScan("petclinicapitesting")
public class Config {
}
