package petclinicapitesting.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import petclinicapitesting.utils.TemplateProcessor;
import petclinicapitesting.utils.restutils.*;

import java.util.Map;

public class CommonSteps implements En {

    @Autowired
    GetRequestFactory getRequestFactory;

    @Autowired
    PostRequestFactory postRequestFactory;

    @Autowired
    PutRequestFactory putRequestFactory;

    @Autowired
    DeleteRequestFactory deleteRequestFactory;

    @Autowired
    TemplateProcessor templateProcessor;

    @Autowired
    ApiResponseStorage apiResponseStorage;

    @Autowired
    RequestEndpointHelpers requestEndpointHelpers;

    public CommonSteps() {
        When("^I get all existing (owners|pets|pet types|specialties|vets|visits)$", (String endPointType) -> {
            String endPoint = requestEndpointHelpers.getRequestEndpointByEndpointType(endPointType);
            getRequestFactory.sendGetRequest(endPoint);
        });

        When("^I get (owner|pet|pet type|specialty|vet|visit) (?:created|deleted|updated) in previous step$", (String endPointType) -> {
            int id = apiResponseStorage.getCurrentResponse().extract().path("id");

            String endPoint = requestEndpointHelpers.getRequestEndpointByEndpointType(endPointType);
            getRequestFactory.sendGetRequest(endPoint + "/" + id);
        });

        When("^I create following (owner|pet|pet type|specialty|vet|visit)$", (String endPointType, DataTable params) -> {
            String endPoint = requestEndpointHelpers.getRequestEndpointByEndpointType(endPointType);
            String requestTemplate = requestEndpointHelpers.getRequestBodyTemplateNameByEndpointType(endPointType);
            Map<String, String> jsonParams = params.asMap();

            String postRequestJsonBody = templateProcessor.processRequestTemplate(requestTemplate, jsonParams);
            postRequestFactory.sendPostRequest(endPoint, postRequestJsonBody);
        });

        When("^I update (owner|pet|pet type|specialty|vet|visit) (?:created|deleted|got|updated) in previous step with following data$", (String endPointType, DataTable params) -> {
            String endPoint = requestEndpointHelpers.getRequestEndpointByEndpointType(endPointType);
            String requestTemplate = requestEndpointHelpers.getRequestBodyTemplateNameByEndpointType(endPointType);
            int id = apiResponseStorage.getCurrentResponse().extract().path("id");

            Map<String, String> jsonParams = params.asMap();
            String postRequestJsonBody = templateProcessor.processRequestTemplate(requestTemplate, jsonParams);
            putRequestFactory.sendPutRequest(endPoint + "/" + id, postRequestJsonBody);
        });

        When("^I update (owner|pet|pet type|specialty|vet|visit) with id (\\d+) using following data$", (String endPointType, Long id, DataTable params) -> {
            String endPoint = requestEndpointHelpers.getRequestEndpointByEndpointType(endPointType);
            String requestTemplate = requestEndpointHelpers.getRequestBodyTemplateNameByEndpointType(endPointType);

            Map<String, String> jsonParams = params.asMap();
            String postRequestJsonBody = templateProcessor.processRequestTemplate(requestTemplate, jsonParams);
            putRequestFactory.sendPutRequest(endPoint + "/" + id, postRequestJsonBody);
        });


        When("^I delete (owner|pet|pet type|specialty|vet|visit) (?:created|deleted|got|updated) in previous step$", (String endPointType) -> {
            String endPoint = requestEndpointHelpers.getRequestEndpointByEndpointType(endPointType);
            int id = apiResponseStorage.getCurrentResponse().extract().path("id");

            deleteRequestFactory.sendDeleteRequest(endPoint + "/" + id);
        });
    }
}
