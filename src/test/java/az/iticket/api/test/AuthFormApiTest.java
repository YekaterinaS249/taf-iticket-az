package az.iticket.api.test;

import az.iticket.api.client.AuthApi;
import az.iticket.api.data.AuthDataFactory;
import az.iticket.api.message.LoginApiMessage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.core.IsEqual.equalTo;

@Epic("Authentication")
@Feature("Login API")
@Owner("Silantyeva Yekaterina")
public class AuthFormApiTest {

    @DisplayName("Login with empty password -API-LOG-001")
    @Test
    public void loginWithEmptyPasswordTest() {
        AuthApi.login(AuthDataFactory.loginWithoutPassword())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.PASSWORD_REQUIRED.getMessage()));
    }

    @DisplayName("Login with empty email -API-LOG-002")
    @Test
    public void loginWithEmptyEmailTest() {
        AuthApi.login(AuthDataFactory.loginWithoutEmail())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.EMAIL_REQUIRED.getMessage()));

    }

    @DisplayName("Login with invalid password -API-LOG-003")
    @Test
    public void loginWithWrongPasswordTest() {
        AuthApi.login(AuthDataFactory.wrongPasswordLogin())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));
    }

    @DisplayName("Login with invalid email -API-LOG-004")
    @Test
    public void loginWithWrongEmailTest() {
        AuthApi.login(AuthDataFactory.wrongEmailLogin())
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));
    }


    @DisplayName("Login with email without '@' symbol -API-LOG-005")
    @Test
    public void loginWithEmailWithoutAtSymbolTest() {
                AuthApi.login(AuthDataFactory.emailWithoutAtSymbols())
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with email without domain part -API-LOG-006")
    @Test
    public void loginWithEmailWithoutDomainTest() {
                AuthApi.login(AuthDataFactory.emailWithoutDomainPart())
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with email without username part -API-LOG-007")
    @Test
    public void emailWithoutUserNamePartTest() {
        AuthApi.login(AuthDataFactory.emailWithoutUserNamePart())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with email containing double '@' -API-LOG-008")
    @Test
    public void emailWithDoubleAtSymbolTest() {
        AuthApi.login(AuthDataFactory.emailWithDoubleAtSymbols())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with Cyrillic characters in email -API-LOG-009")
    @Test
    public void emailWithUserNameCyrillicTest(){
        AuthApi.login(AuthDataFactory.emailUserNameCyrilic())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with leading space in email -API-LOG-010")
    @Test
    public void emailContainsLeadingSpaceTest() {
        AuthApi.login(AuthDataFactory.emailContainsStartingSpace())
                .then()
                .statusCode(403)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));

    }

    @DisplayName("Login with space in the middle of email -API-LOG-011")
    @Test
    public void emailContainsMiddleSpaceTest() {
        AuthApi.login(AuthDataFactory.emailContainsMiddleSpace())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));

    }


    @DisplayName("Login with trailing space in email -API-LOG-012")
    @Test
    public void  emailContainsTrailingSpaceTest() {
        AuthApi.login(AuthDataFactory.emailContainsEndingSpace())
                .then()
                .statusCode(403)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));

    }

    @DisplayName("Login with domain starting with dot -API-LOG-013")
    @Test
    public void emailDomainPartStarsWithDotTest() {
        AuthApi.login(AuthDataFactory.emailDomainPartStarsWithDot())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with tab character in email -API-LOG-014")
    @Test
    public void emailContainsTabCharactersTest() {
        AuthApi.login(AuthDataFactory.emailWithTabCharacters())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with newline character in email -API-LOG-015")
    @Test
    public void emailContainsNewLineCharactersTest() {
        AuthApi.login(AuthDataFactory.emailWithNewLineCharacters())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @DisplayName("Login with Cyrillic password -API-LOG-016")
    @Test
    public void loginContainsCyrillicPasswordTest() {
        AuthApi.login(AuthDataFactory.passwordCyrillic())
                .then()
                .statusCode(403)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));
    }

    @DisplayName("Login with email exceeding maximum length -API-LOG-017")
    @Test
    public void loginWithLongEmailTest() {
                AuthApi.login(AuthDataFactory.emailWithMaxLength())
                        .then()
                .statusCode(422)
                .body("response[0].messages[1]", equalTo(LoginApiMessage.MAX_EMAIL_LENGTH.getMessage()));
    }

    @DisplayName("Login with password shorter than minimum length -API-LOG-018")
    @Test
    public void loginWithShortPasswordTest() {
                AuthApi.login(AuthDataFactory.passwordTooShort())
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.MIN_PASSWORD_LENGTH.getMessage()));

    }

    @DisplayName("Login with invalid password length -API-LOG-019")
    @Test
    public void loginWithInvalidLengthTest() {
                AuthApi.login(AuthDataFactory.passwordInvalidLength())
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.MIN_PASSWORD_LENGTH.getMessage()));

    }

    @DisplayName("Login with password containing only spaces -API-LOG-020")
    @Test
    public void loginWithPasswordOnlySpacesTest() {
        AuthApi.login(AuthDataFactory.passwordOnlySpaces())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.PASSWORD_REQUIRED.getMessage()));

    }

    @DisplayName("Login with email containing only spaces -API-LOG-021")
    @Test
    public void loginWithEmailOnlySpacesTest() {
       AuthApi.login(AuthDataFactory.emailOnlySpaces())
               .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo(LoginApiMessage.EMAIL_REQUIRED.getMessage()));
    }

    @DisplayName("Login with password exceeding maximum length -API-LOG-022")
    @Test
    public void loginPasswordMaxLength() {
        AuthApi.login(AuthDataFactory.passwordMaxLength())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.MAX_PASSWORD_LENGTH.getMessage()));
    }

    @DisplayName("Login with unregistered user -API-LOG-023")
    @Test
    public void loginNotRegisterUserTest(){
        AuthApi.login(AuthDataFactory.notRegisteredUserLogin())
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo(LoginApiMessage.INVALID_CREDENTIALS.getMessage()));

    }

    @DisplayName("Login without input email -API-LOG-024")
    @Test
    public void errorWithoutEmailInputTest(){
        AuthApi.login(AuthDataFactory.withoutEmailInput())
                .then()
                .statusCode(422);
    }

    @DisplayName("Login without input password -API-LOG-025")
    @Test
    public void errorWithoutPasswordInputTest(){
        AuthApi.login(AuthDataFactory.withoutPasswordInput())
                .then()
                .statusCode(422);
    }
}

