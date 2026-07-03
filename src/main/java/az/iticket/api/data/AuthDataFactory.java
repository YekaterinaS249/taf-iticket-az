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


    public static LoginRequest emailWithoutAtSymbols() {
        return new LoginRequest(
                "useruser.com",
                faker.internet().password());
    }

    public static LoginRequest emailWithoutDomainPart() {
        return new LoginRequest(
                "user123",
                faker.internet().emailAddress());
    }

    public static LoginRequest emailWithoutUserNamePart() {
        return new LoginRequest(
                "@user.com",
                faker.internet().password());
    }

    public static LoginRequest emailWithDoubleAtSymbols() {
        return new LoginRequest(
                "user@@gmail.com",
                faker.internet().password());
    }

    public static LoginRequest emailDomainPartStarsWithDot() {
        return new LoginRequest(
                "user@.test.com",
                faker.internet().password());
    }

    public static LoginRequest emailUserNameCyrillic() {
        return new LoginRequest(
                "юзер@gmail.com",
                faker.internet().password());
    }

    public static LoginRequest emailContainsStartingSpace() {
        return new LoginRequest(
                " user@user.com",
                faker.internet().password());
    }

    public static LoginRequest emailContainsMiddleSpace(){
        return new LoginRequest(
                " user@ user.com",
                faker.internet().password());
    }

    public static LoginRequest emailContainsEndingSpace(){
        return new LoginRequest(
                "user@user.com ",
                faker.internet().password());
    }

    public static LoginRequest emailWithTabCharacters() {
        return new LoginRequest(
                "user\t@user.com",
                faker.internet().password());
    }

    public static LoginRequest emailWithNewLineCharacters() {
        return new LoginRequest(
                "user\n@user.com",
                faker.internet().password());
    }

    public static LoginRequest emailWithMaxLength() {
        String email = "a".repeat(64) + "@" + "b".repeat(187) + ".com";
        return new LoginRequest(email, "maryam17");

    }

    public static LoginRequest passwordTooShort() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                "m");
    }

    public static LoginRequest passwordInvalidLength() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                "test123");
    }

    public static LoginRequest passwordOnlySpaces() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                "         ");
    }

    public static LoginRequest passwordCyrillic() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                "марьям17");
    }

    public static LoginRequest emailOnlySpaces() {
        return new LoginRequest(
                "          ",
                faker.internet().password());
    }

    public static LoginRequest passwordMaxLength() {
        String password = "a".repeat(256);
        return new LoginRequest(
                faker.internet().emailAddress(),
                password);
    }

    public static LoginRequest notRegisteredUserLogin() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                faker.internet().password());
    }

    public static LoginRequest withoutEmailInput() {
            LoginRequest request = new LoginRequest();
            request.setPassword(faker.internet().password(8, 8));
            return request;
    }

    public static LoginRequest withoutPasswordInput() {
        LoginRequest request = new LoginRequest();
        request.setEmail(faker.internet().emailAddress());
        return request;
    }

    public static LoginRequest withInvalidCredentials() {
        return new LoginRequest(
                faker.internet().emailAddress(),
                faker.internet().password());
    }
}



