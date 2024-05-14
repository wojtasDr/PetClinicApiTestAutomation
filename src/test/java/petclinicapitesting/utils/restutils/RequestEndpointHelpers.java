package petclinicapitesting.utils.restutils;

import org.springframework.stereotype.Component;

import static petclinicapitesting.utils.restutils.RequestsEndpoints.*;

@Component
public class RequestEndpointHelpers {
    public String getRequestEndpointByEndpointType(String endPointType) {
        String endPoint;
        switch (endPointType) {
            case "owners", "owner" -> endPoint = OWNERS.getEndPoint();
            case "pets", "pet" -> endPoint = PETS.getEndPoint();
            case "pet types", "pet type" -> endPoint = PET_TYPES.getEndPoint();
            case "specialty", "specialties" -> endPoint = SPECIALTIES.getEndPoint();
            case "vets", "vet" -> endPoint = VETS.getEndPoint();
            case "visit", "visits" -> endPoint = VISITS.getEndPoint();
            default -> throw new IllegalStateException("Invalid endPointType: " + endPointType);
        }
        return endPoint;
    }

    public String getRequestBodyTemplateNameByEndpointType(String endPointType) {
        String requestBodyTemplate;
        switch (endPointType) {
            case "owner" -> requestBodyTemplate = OWNERS.getRequestBodyTemplateName();
            case "pet" -> requestBodyTemplate = PETS.getRequestBodyTemplateName();
            case "pet type" -> requestBodyTemplate = PET_TYPES.getRequestBodyTemplateName();
            case "specialty" -> requestBodyTemplate = SPECIALTIES.getRequestBodyTemplateName();
            case "vet" -> requestBodyTemplate = VETS.getRequestBodyTemplateName();
            case "visit" -> requestBodyTemplate = VISITS.getRequestBodyTemplateName();
            default -> throw new IllegalStateException("Invalid endPointType: " + endPointType);
        }
        return requestBodyTemplate;
    }
}
