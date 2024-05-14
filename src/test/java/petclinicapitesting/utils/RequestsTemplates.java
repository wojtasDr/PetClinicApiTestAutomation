package petclinicapitesting.utils;

import lombok.Getter;

public enum RequestsTemplates {
    PET_TYPE_REQUEST_TEMPLATE("petTypesRequestBody.ftl"),
    VETS_REQUEST_TEMPLATE("vetsRequestBody.ftl");

    @Getter
    private final String name;

    RequestsTemplates(String name) {
        this.name = name;
    }

}
