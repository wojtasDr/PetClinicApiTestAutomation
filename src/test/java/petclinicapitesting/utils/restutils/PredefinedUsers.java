package petclinicapitesting.utils.restutils;

import lombok.Getter;

public enum PredefinedUsers {
    ADMIN_USER("admin", "admin");

    @Getter
    private final String userName;
    @Getter
    private final String password;

    PredefinedUsers(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
