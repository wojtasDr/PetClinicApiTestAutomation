package petclinicapitesting.utils.restutils;

import lombok.Getter;


public class User {
    @Getter
    private final String useName;
    @Getter
    private final String password;

    public User(String useName, String password) {
        this.useName = useName;
        this.password = password;
    }

}
