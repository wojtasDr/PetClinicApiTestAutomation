package petclinicapitesting.steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Value;

public class Hooks {

    @Value("${pet.clinic.base.uri}")
    private String petClinicBaseUri;

    @Before
    public void setBaseUrl() {
        RestAssured.baseURI = petClinicBaseUri;
    }
}
