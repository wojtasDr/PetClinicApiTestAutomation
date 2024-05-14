package petclinicapitesting.steps;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import petclinicapitesting.utils.restutils.ApiResponseStorage;
import petclinicapitesting.utils.restutils.GetRequestFactory;
import petclinicapitesting.utils.restutils.PredefinedUsers;
import petclinicapitesting.utils.restutils.User;

import static petclinicapitesting.utils.restutils.RequestsEndpoints.HEALTH_CHECK;

public class HealthCheckSteps implements En {

    @Autowired
    GetRequestFactory getRequestFactory;

    public HealthCheckSteps() {
        Given("^Pet clinic application is up and running$", () -> getRequestFactory.sendGetRequest(HEALTH_CHECK.getEndPoint()).statusCode(HttpStatus.SC_OK));
    }
}
