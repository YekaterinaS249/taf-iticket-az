package az.iticket.ui.test;

import az.iticket.ui.basetest.BaseTest;
import az.iticket.ui.message.RecoverPassMessage;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RecoverPasswordPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Authentication")
@Feature("Recovery Password")
@Owner("Silantyeva Yekaterina")
public class RecoverPasswordTest extends BaseTest {
    private AuthPage authPage;
    private RecoverPasswordPage recoverPasswordPage;

    @BeforeEach
    public void initRecoverPasswordPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage();
        authPage.clickForgotPasswordButton();
        recoverPasswordPage = new RecoverPasswordPage();
    }

    @DisplayName("Verify recover password page title - UI-RCP-001")
    @Severity(SeverityLevel.MINOR)
    @Story("UI Elements")
    @Test
    public void shouldDisplayRecoverPasswordTitleTest() {
        assertEquals("Забыли пароль?", recoverPasswordPage.getRecoverPasswordTitleTest(),
                "Title should be displayed");
    }

    @DisplayName("Verify email validation for invalid email formats")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-RCP-002, '@gmail.com'",
            "UI-RCP-003,'user'",
            "UI-RCP-004 ,'user.user@@gmail.com'",
            "UI-RCP-005, ''user@.test.com",
            "UI-RCP-006, 'юзер@gmail.com'",
            "UI-RCP-007, 'usertest.com"
    })
    public void shouldShowInvalidEmailErrorForInvalidEmailFormatsTest(String testId, String email) {
        recoverPasswordPage.setInputEmail(email);
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.INVALID_EMAIL.getMessage(), recoverPasswordPage.getInvalidEmailErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Verify email validation for tab and newline characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-RCP-008,'test\\t@test.com'",
            "UI-RCP-009, 'test\\n@test.com"
    })
    public void shouldShowInvalidEmailErrorForEmailWithTabAndNewLineCharactersTest(String testId, String email) {
        recoverPasswordPage.setInputEmail(email);
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.INVALID_EMAIL.getMessage(), recoverPasswordPage.getInvalidEmailErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Verify email validation for emails containing spaces")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-RCP-010,' test@test.com",
            "UI-RCP-011, 'user @user.com'",
            "UI-RCP-012, 'user@user.com ",
            "UI-RCP-013, '       '"
    })
    public void shouldShowInvalidEmailErrorForEmailContainingSpacesTest(String testId, String email) {
        recoverPasswordPage.setInputEmail(email);
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.INVALID_EMAIL.getMessage(), recoverPasswordPage.getInvalidEmailErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Verify email validation for incorrect email structure")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Validation")
    @ParameterizedTest(name = "Email: {0}")
    @CsvSource({
            "UI-RCP-014,'user@test'",
            "UI-RCP-015, 'user.@test.com'"
    })
    public void shouldShowInvalidEmailFormatErrorForIncorrectEmailStructureTest(String testId, String email) {
        recoverPasswordPage.setInputEmail(email);
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.INVALID_EMAIL_FORMAT.getMessage(), recoverPasswordPage.getInvalidEmailFormatErrorMessage(),
                "Invalid email format message should be displayed");
    }

    @DisplayName("Verify validation message for empty email input -UI-RCP-016")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Validation")
    @Test
    public void shouldShowEmailRequiredErrorForEmptyEmailTest() {
        recoverPasswordPage.setInputEmail("");
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.EMAIL_REQUIRED.getMessage(), recoverPasswordPage.getEmailRequiredErrorMessage(),
                "Email required message should be displayed");
    }

    @DisplayName("Verify validation message for email exceeding maximum length -UI-RCP-017")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Validation")
    @Test
    public void shouldShowMaxLengthErrorForTooLongEmailTest() {
        String email = "a".repeat(246) + "@gmail.com";
        recoverPasswordPage.setInputEmail(email);
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.MAX_EMAIL_LENGTH.getMessage(), recoverPasswordPage.getMaxLengthEmailErrorMessage(),
                "Email length should be maximum length error message");
    }

    @DisplayName("Verify success message after password reset request for registered user -UI-RCP-018")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Recover Password")
    @Test
    public void shouldDisplaySuccessMessageForRegisteredUserTest() {
        recoverPasswordPage.setInputEmail("omnqqmvgtlixqnwjtp@jbsze.com");
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.RESET_LINK_SENT.getMessage(), recoverPasswordPage.getSuccessMessageSendEmail(),
                "Success message should be displayed");

    }

    @DisplayName("Verify success message after password reset request for unregistered user -UI-RCP-019")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Recover Password")
    @Test
    public void shouldDisplaySuccessMessageForUnregisteredUserTest() {
        recoverPasswordPage.setInputEmail("ecrxsrutjwlvbxfovx@vtmpj.com");
        recoverPasswordPage.clickResetPasswordButton();
        assertEquals(RecoverPassMessage.RESET_LINK_SENT.getMessage(), recoverPasswordPage.getSuccessMessageSendEmail(),
                "Success message should be displayed");
    }

    @DisplayName("Verify login modal is displayed after clicking Back to Login button -UI-RCP-020")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigation")
    @Test
    public void lshouldDisplayLoginModalAfterClickingBackToEnterButtonTest() {
        recoverPasswordPage.clickBackToEnterButton();
        assertTrue(recoverPasswordPage.isRecoverPasswordModalInvisible(), "Login Window should be displayed");
    }

    @DisplayName("Verify recover password modal is closed after clicking Close button -UI-RCP-021")
    @Severity(SeverityLevel.NORMAL)
    @Story("Modal Window")
    @Test
    public void shouldCloseRecoverPasswordModalAfterClickingCloseButtonTest() {
        recoverPasswordPage.clickCloseButton();
        assertTrue(recoverPasswordPage.isRecoverPasswordModalWindowInvisible(), "Modal Window should be displayed");
    }

    @DisplayName("Verify recover password informational message -UI-RCP-022")
    @Severity(SeverityLevel.MINOR)
    @Story("UI Elements")
    @Test
    public void shouldDisplayRecoverPasswordMessageTest() {
        assertEquals(RecoverPassMessage.RESET_PASSWORD.getMessage(), recoverPasswordPage.getResetPasswordMessage(),
                "Reset password message should be displayed");
    }

    @DisplayName("Verify email input placeholder text -UI-RCP-023")
    @Severity(SeverityLevel.MINOR)
    @Story("UI Elements")
    @Test
    public void shouldDisplayEmailPlaceholderTest() {
        assertEquals("name@example.com", recoverPasswordPage.getEmailPlaceholderText(),
                "Email placeholder text should be displayed");
    }
}


