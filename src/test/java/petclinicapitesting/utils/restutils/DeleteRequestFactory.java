package petclinicapitesting.utils.restutils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static petclinicapitesting.utils.restutils.PredefinedUsers.ADMIN_USER;

@Component
public class DeleteRequestFactory {

    @Autowired
    ApiResponseStorage apiResponseStorage;

    public ValidatableResponse sendDeleteRequest(String endPoint) {
        apiResponseStorage.setCurrentResponse(
                RestAssured
                        .given()
                        .log().all()
                        .auth()
                        .preemptive()
                        .basic(ADMIN_USER.getUserName(), ADMIN_USER.getPassword())
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(endPoint)
                        .then()
                        .log().all()
                        .assertThat());

        return apiResponseStorage.getCurrentResponse();
    }
}
