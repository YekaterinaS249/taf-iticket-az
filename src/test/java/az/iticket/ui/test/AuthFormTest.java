package az.iticket.ui.test;

import az.iticket.ui.basetest.BaseTest;
import az.iticket.ui.message.LoginMessage;
import az.iticket.ui.pages.AuthPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


@Epic("Authentication")
@Feature("Login")
@Owner("Yekaterina Silantyeva")

public class AuthFormTest extends BaseTest {
    private AuthPage authPage;

    @BeforeEach
    public void initAuthPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage();
    }

    @DisplayName("Verify login with invalid password -UI-LOG-001")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login validation")
    @Test
    public void loginWithWrongPassword() {
        authPage.setInputEmail("omnqqmvgtlixqnwjtp@jbsze.com");
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS_ERROR_MESSAGE, authPage.getErrorInvalidCredentials());

    }

    @DisplayName("Verify login with non-registered user -UI-LOG-002")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login validation")
    @Test
    public void verifyLoginWithWrongEmail() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("shelovespizza12");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.USER_NOT_FOUND_MESSAGE, authPage.getErrorMessageUserNotFound());
    }

    @DisplayName("Verify invalid email credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-LOG-003, '@gmail.com'",
            "UI-LOG-004,'test'",
            "UI-LOG-005, 'user.user@@gmail.com'",
            "UI-LOG-006,'user@.test.com'",
            "UI-LOG-007, 'юзер@gmail.com'"
    })
    void verifyInvalidEmailTest(String testId, String email) {
        authPage.setInputEmail(email);
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE, authPage.getErrorInvalidEmail());

    }

    @DisplayName("Verify email validation with spaces")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-LOG-008, ' user@test.com'",
            "UI-LOG-009, 'user @test.com'",
            "UI-LOG-010, 'user@test.com '",
            "UI-LOG-011, '      '"
    })
    void loginWithSpacesInEmailTest(String testId, String email) {
        authPage.setInputEmail(email);
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Verify validation invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-LOG-012, 'user@test'",
            "UI-LOG-013, 'user.@test.com'"
    })
    void loginWithInvalidEmailFormatTest(String testId, String email) {
        authPage.setInputEmail(email);
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_FORMAT_EMAIL_MESSAGE, authPage.getErrorMessageInvalidEmailFormat());
    }

    @DisplayName("Verify email max length validation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validation")
    @ParameterizedTest(name = "UI-LOG-014, UI-LOG-015 {0}")
    @ValueSource(ints = {254, 255})
    void emailWithMaxAllowedLengthTest(int length) {
        String email = "a".repeat(length - 10) + "@gmail.com";
        authPage.setInputEmail(email);
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_FORMAT_EMAIL_MESSAGE, authPage.getErrorMessageInvalidEmailFormat());
    }

    @DisplayName("Verify email validation with tab and newline characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-LOG-016,'user\\t@test.com",
            "UI-LOG-017,'user\\n@test.com"
    })
    void emailWithTabAndNewLineTest(String testId, String email) {
        authPage.setInputEmail(email);
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Verify email validation with 256 characters -UI-LOG-018")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void emailValidationWith256Characters() {
        String email = "a".repeat(246) + "@gmail.com";
        authPage.setInputEmail(email);
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.MAX_LENGTH_EMAIL_ERROR_MESSAGE, authPage.getErrorMessageMaxLengthEmail());
    }

    @DisplayName("Verify empty email validation -UI-LOG-019")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email validation")
    @Test
    public void loginWithEmptyEmailTest() {
        authPage.setInputEmail("");
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.EMAIL_REQUIRED_MESSAGE, authPage.getErrorMessageEmptyInputEmail());
    }

    @DisplayName("Verify password validation by length")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password validation")
    @ParameterizedTest(name = "{0} | length = {1}")
    @CsvSource({
            "UI_LOG-020, 0",
            "UI-LOG-021, 1",
            "UI-LOG-022, 5"
    })
    void passwordValidationByLengthTest(String testId, int length) {
        String password = "a".repeat(length);
        authPage.setInputEmail("test@test.com");
        authPage.setInputPassword(password);
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.MIN_LENGTH_PASSWORD_ERROR_MESSAGE, authPage.getErrorLengthPassword());

    }

    @DisplayName("Verify password validation error disappears after valid input -UI-LOG-023")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password validation")
    @Test
    public void passwordValidationErrorDisappearsAfterEntering6CharactersTest() {
        authPage.setInputEmail("user@user.com");
        authPage.setInputPassword("user12");
        authPage.clickSubmitButton();
        Assertions.assertTrue(authPage.isPasswordValidationErrorNotDisplayed());

    }

    //В системе присутвует разночтение бэка и фронта на валидацию пароля.
    @DisplayName("Verify password validation for 7-character password -UI-LOG-024")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password validation")
    @Test
    public void passwordValidationFor7CharacterPasswordTest() {
        authPage.setInputEmail("user@user.com");
        authPage.setInputPassword("user123");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.PASSWORD_TOO_SHORT, authPage.getErrorShortPassword());
    }

    @DisplayName("Verify password max length validation -UI-LOG-025")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password validation")
    @Test
    public void passwordValidationByMaxLengthTest() {
        String password = ("a21").repeat(85) + "A";
        authPage.setInputEmail("user@user.com");
        authPage.setInputPassword(password);
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.MAX_LENGTH_PASSWORD_ERROR_MESSAGE, authPage.getErrorPasswordMaxLength());
    }

    @DisplayName("Verify password with only spaces -UI-LOG-026")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password validation")
    @Test
    public void passwordValidationWithOnlySpacesTest() {
        authPage.setInputEmail("user@user.com");
        authPage.setInputPassword("       ");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.EMPTY_PASSWORD_ERROR_MESSAGE, authPage.getErrorMessageEmptyInputPassword());
    }

    @DisplayName("Verify auth page title -UI-LOG-027")
    @Severity(SeverityLevel.MINOR)
    @Story("UI elements")
    @Test
    public void authPageTitleTest() {
        Assertions.assertEquals("Войти", authPage.getAuthTitle());
    }

    @DisplayName("Verify auth page footer title -UI-LOG-028")
    @Severity(SeverityLevel.MINOR)
    @Story("UI elements")
    @Test
    public void authPageFooterTitleTest() {
        Assertions.assertEquals("Нет аккаунта?", authPage.getFooterAuthTitle());
    }

    @DisplayName("Verify email placeholder text -UI-LOG-029")
    @Severity(SeverityLevel.MINOR)
    @Story("UI elements")
    @Test
    public void placeholderTextInEmailInputTest() {
        Assertions.assertEquals("name@example.com", authPage.getEmailPlaceholder());

    }

    @DisplayName("Verify password visibility toggle -UI-LOG-030")
    @Severity(SeverityLevel.MINOR)
    @Story("UI elements")
    @Test
    public void passwordVisibilityToggleTest() {
        authPage.setInputPassword("12345678");
        Assertions.assertEquals("password", authPage.getPasswordFieldType());
        authPage.clickTogglePasswordButton();
        Assertions.assertEquals("text", authPage.getPasswordFieldType());
    }

    @DisplayName("Verify recover password modal is closed after clicking Close button  -UI-LOG-031")
    @Severity(SeverityLevel.MINOR)
    @Story("Modal Window")
    @Test
    public void modalWindowLoginNotDisplayedTest() {
        authPage.clickCloseButton();
        Assertions.assertTrue(authPage.isModalLoginInvisible());

    }
}
