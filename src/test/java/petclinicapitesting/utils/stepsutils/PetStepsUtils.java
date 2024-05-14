package petclinicapitesting.utils.stepsutils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petclinicapitesting.utils.restutils.GetRequestFactory;

import java.util.Optional;

import static petclinicapitesting.utils.restutils.RequestsEndpoints.PET_TYPES;

@Component
public class PetStepsUtils {

    @Autowired
    GetRequestFactory getRequestFactory;

    public String findPetTypeIdByName(String petTypeName) {
        ValidatableResponse getPetTypesResponse = getRequestFactory.sendGetRequest(PET_TYPES.getEndPoint());
        getPetTypesResponse.statusCode(HttpStatus.SC_OK);

        Optional<JsonObject> foundId = getPetTypesResponse.extract().jsonPath().getList("$")
                .stream().map(eD -> new Gson().fromJson(eD.toString(), JsonObject.class))
                .filter(eD -> eD.get("name").toString().equals("\"" + petTypeName + "\""))
                .reduce((first, second) -> second);

        return foundId.orElseThrow().get("id").toString();
    }
}
