package az.iticket.api.data;

import az.iticket.api.model.LoginRequest;
import net.datafaker.Faker;

public class AuthDataFactory {

    private static Faker faker = new Faker();

    public static LoginRequest loginWithoutPassword() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                "");
    }

    public static LoginRequest loginWithoutEmail() {
        return new LoginRequest(
                "",
                faker.internet().password());
    }

    public static LoginRequest wrongPasswordLogin() {
        return new LoginRequest(
                "omnqqmvgtlixqnwjtp@jbsze.com",
                "user1234");
    }

    public static LoginRequest wrongEmailLogin() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                "shelovespizza12");
    }

    public static LoginRequest emptyEmailAndPasswordLogin() {
        return new LoginRequest(
                "",
                "");
    }
}
