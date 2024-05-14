package petclinicapitesting.utils.restutils;

import lombok.Getter;

public enum RequestsEndpoints {
    HEALTH_CHECK("/actuator/health", null),
    OWNERS("/api/owners", "ownersRequestBody.ftl"),
    PETS("/api/pets", "petsRequestBody.ftl"),
    PET_TYPES("/api/pettypes", "petTypesRequestBody.ftl"),
    SPECIALTIES("/api/specialties", "specialtiesRequestBody.ftl"),
    VETS("/api/vets", "vetsRequestBody.ftl"),
    VISITS("/api/visits", "visitsRequestBody.ftl");

    @Getter
    private final String endPoint;

    @Getter
    private final String requestBodyTemplateName;

    RequestsEndpoints(String endPoint, String requestBodyTemplateName) {
        this.endPoint = endPoint;
        this.requestBodyTemplateName = requestBodyTemplateName;
    }
}
