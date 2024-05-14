package petclinicapitesting.steps;

import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import petclinicapitesting.utils.RequestsTemplates;
import petclinicapitesting.utils.TemplateProcessor;
import petclinicapitesting.utils.restutils.DeleteRequestFactory;
import petclinicapitesting.utils.restutils.GetRequestFactory;
import petclinicapitesting.utils.restutils.PostRequestFactory;
import petclinicapitesting.utils.restutils.PutRequestFactory;
import petclinicapitesting.utils.stepsutils.PetStepsUtils;

import java.util.Map;

import static petclinicapitesting.utils.restutils.RequestsEndpoints.PET_TYPES;

public class PetTypesSteps implements En {

    @Autowired
    private GetRequestFactory getRequestFactory;

    @Autowired
    private PostRequestFactory postRequestFactory;

    @Autowired
    private PutRequestFactory putRequestFactory;

    @Autowired
    private DeleteRequestFactory deleteRequestFactory;

    @Autowired
    private TemplateProcessor templateProcessor;

    @Autowired
    private PetStepsUtils petStepsUtils;

    public PetTypesSteps() {
//        When("^I get all existing pet types$", () ->
//                getRequestFactory.sendGetRequest(PET_TYPES.getEndPoint()));

//        When("^I create following pet type$", (DataTable params) -> {
//            Map<String, String> jsonParams = params.transpose().asMap();
//            String postRequestJsonBody = templateProcessor.processRequestTemplate(RequestsTemplates.PET_TYPE_REQUEST_TEMPLATE.getName(), jsonParams);
//
//            postRequestFactory.sendPostRequest(PET_TYPES.getEndPoint(), postRequestJsonBody);
//        });

        When("Create pet type {string}", (String petTypeName) -> {
            String postRequestJsonBody = templateProcessor.processRequestTemplate(RequestsTemplates.PET_TYPE_REQUEST_TEMPLATE.getName(), Map.of("name", petTypeName));
            postRequestFactory.sendPostRequest(PET_TYPES.getEndPoint(), postRequestJsonBody);

        });

        When("Update pet type {string} and set {string}", (String oldPetTypeName, String newPetTypeName) -> {
            String id = petStepsUtils.findPetTypeIdByName(oldPetTypeName);
            String putRequestJsonBody = templateProcessor
                    .processRequestTemplate(RequestsTemplates.PET_TYPE_REQUEST_TEMPLATE.getName(), Map.of("name", newPetTypeName));
            putRequestFactory.sendPutRequest(PET_TYPES.getEndPoint() + "/" + id, putRequestJsonBody);

        });

        When("Update pet type with id {long} and set {string}", (Long petTypeId, String newPetTypeName) -> {
            String putRequestJsonBody = templateProcessor
                    .processRequestTemplate(RequestsTemplates.PET_TYPE_REQUEST_TEMPLATE.getName(), Map.of("name", newPetTypeName));
            putRequestFactory.sendPutRequest(PET_TYPES.getEndPoint() + "/" + petTypeId.toString(), putRequestJsonBody);

        });

        When("Delete pet type {string}", (String petTypeNameToBeDeleted) -> {
            String id = petStepsUtils.findPetTypeIdByName(petTypeNameToBeDeleted);
            deleteRequestFactory.sendDeleteRequest(PET_TYPES.getEndPoint() + "/" + id);
        });
    }
}
