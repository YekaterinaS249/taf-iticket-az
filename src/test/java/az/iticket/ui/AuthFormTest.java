package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import az.iticket.ui.message.LoginMessage;
import az.iticket.ui.pages.AuthPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;


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

    @DisplayName("UI-LOG-001 -Verify validation error empty email")
    @Story("Submit login form with empty email")
    @Test
    public void loginWithEmptyEmailTest() {
        authPage.setInputEmail("");
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.EMAIL_REQUIRED_MESSAGE, authPage.getErrorMessageEmptyInputEmail());
    }

    @DisplayName("UI-LOG-002 -Verify validation error email without username")
    @Story("Submit login form")
    @Test
    public void loginWithEmptyUsernameTest() {
        authPage.setInputEmail("@gmail.com");
        authPage.setInputPassword("user1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE, authPage.getErrorInvalidEmail());
    }

    @DisplayName("UI-LOG-003 -Verify validation email without domain part")
    @Story("Submit login form")
    @Test
    public void loginWithEmptyDomainPartTest() {
        authPage.setInputEmail("test");
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE, authPage.getErrorInvalidEmail());
    }

    @DisplayName("UI_LOG-005 -Verify validation error empty password")
    @Story("Submit login form with empty password")
    @Test
    public void loginWithEmptyPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.PASSWORD_INVALID_LENGTH_MESSAGE, authPage.getErrorLengthPassword());
    }

}