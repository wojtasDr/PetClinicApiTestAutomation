package petclinicapitesting.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import petclinicapitesting.utils.restutils.ApiResponseStorage;

public class ResponseValidationSteps implements En {

    @Autowired
    ApiResponseStorage apiResponseStorage;

    public ResponseValidationSteps() {
        Then("The response status code equals {int}", (Integer expectedStatus) ->
                apiResponseStorage.getCurrentResponse().statusCode(expectedStatus));

        Then("^The response contains list$", () -> {
            Assertions.assertThat(apiResponseStorage.getCurrentResponse().extract().jsonPath().getList("$")
                    .size()).isGreaterThanOrEqualTo(0);
        });

        Then("^The response contains the following$", (DataTable expectedDataTable) -> {
            ResponseBodyExtractionOptions currentResponseBody = apiResponseStorage.getCurrentResponse().extract().body();
            try {
                expectedDataTable.asMap().forEach((k, v) -> Assertions.assertThat(currentResponseBody.path(k).toString()).isEqualTo(v));
            } catch (JsonPathException ex) {
                if (currentResponseBody.asPrettyString().isEmpty()) {
                    Assert.fail("Response body is empty!");
                } else {
                    Assert.fail("Response body is NOT a valid JSON: " + currentResponseBody.asPrettyString());
                }
            }
        });


    }
}
