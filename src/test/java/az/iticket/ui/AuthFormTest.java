package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import az.iticket.message.LoginMessage;
import az.iticket.ui.pages.AuthPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
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

    @DisplayName("Check login with valid data")
    @Story("Login with valid credentials")
    @Test
    public void loginWithValidCredentialsTest() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("aska1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.LOGIN_SUCCESS_MESSAGE, authPage.getLoginSuccessMessage());
    }

    @DisplayName("Cheсk submit login form with Enter button")
    @Story("Submit login form using Enter key")
    @Test
    public void submitLoginFormWithEnter() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("aska1234");
        authPage.submitLoginFormWithEnter();
        Assertions.assertEquals(LoginMessage.LOGIN_SUCCESS_MESSAGE, authPage.getLoginSuccessMessage());
    }

    @DisplayName("Chek submit empty e-mail")
    @Story("Submit login form with empty email")
    @Test
    public void loginWithEmptyEmailTest() {
        authPage.setInputEmail("");
        authPage.setInputPassword("aska1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_EMAIL, authPage.getErrorMessageEmptyInputEmail());
    }

    @DisplayName("Chek submit empty password")
    @Story("Submit login form with empty password")
    @Test
    public void loginWithEmptyPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_PASSWORD, authPage.getErrorMessageEmptyInputPassword());
    }

    @DisplayName("Login with invalid email (without @)")
    @Story("Login with invalid email format")
    @Test
    public void loginWithInvalidEmailTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkzvtmpj.com");
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_FORMAT, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Chek login with wrong e-mail")
    @Story("Login with wrong email")
    @Test
    public void loginWithWrongEmailTest() {
        authPage.setInputEmail("thsmklqhnxyuqzfngl@jbsze.com");
        authPage.setInputPassword("aska1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Chek login with e-mail only spaces")
    @Story("Login with email containing only spaces")
    @Test
    public void loginWithEmailOnlySpacesTest() {
        authPage.setInputEmail("            ");
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_EMAIL, authPage.getErrorInvalidEmail());
    }


    @DisplayName("Chek login with long email")
    @Story("Login with long email")
    @Test
    public void loginWithLongEmailTest() {
        String email = "a".repeat(64) + "@" + "b".repeat(187) + ".com";
        authPage.setInputEmail(email);
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals("Количество символов в поле e-mail адрес не может превышать 255." ,authPage.getErrorMessageLongEmail());

    }

    @DisplayName("Chek login with wrong password")
    @Story("Login with invalid password")
    @Test
    public void loginWithWrongPasswordTest() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("12345678");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorMessageInvalidCredentials());
    }

    @DisplayName("Chek login with short password")
    @Story("Login with short password")
    @Test
    public void loginWithShortPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("t");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.PASSWORD_TOO_SHORT, authPage.getErrorMessageShortPassword());
    }

    @DisplayName("Chek login test with invalid password length")
    @Story("Login with invalid password length")
    @Test
    public void loginWithInvalidPasswordLengthTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("test123");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.PASSWORD_TOO_SHORT, authPage.getErrorMessageShortPassword());
    }

    @DisplayName("Chek login with long password")
    @Story("Login with long password")
    @Test
    public void loginWithLongPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("test12345");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorMessageInvalidCredentials());
    }

    @DisplayName("Check login with password contains only spaces")
    @Story("Login with whitespace-only password")
    @Test
    public void loginWithPasswordOnlySpacesTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("        ");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_PASSWORD, authPage.getErrorMessageEmptyInputPassword());

    }

    @DisplayName("Chek get auth title")
    @Story("Get authentication page title")
    @Test
    public void getAuthTitleTest() {
        Assertions.assertEquals("Войти", authPage.getAuthTitle());
    }

    @DisplayName("Chek get footer auth title")
    @Story("Verify footer authentication title")
    @Test
    public void getFooterAuthTitleTest() {
        Assertions.assertTrue(authPage.getFooterAuthTitle().contains("Впервые на iTicket.AZ?"));
        System.out.println(authPage.getFooterAuthTitle());
    }

    @DisplayName("Chek login not register user")
    @Story("Login with unregistered user")
    @Test
    public void loginNotRegisterUserTest() {
        authPage.setInputEmail("katyatest97@gmail.com");
        authPage.setInputPassword("katya199");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorMessageInvalidCredentials());
    }


    @DisplayName("Chek get placeholder text in password input")
    @Story("Password input placeholder is displayed")
    @Test
    public void getAttributeInPasswordInputTest() {
        authPage.getPasswordPlaceholder();
        Assertions.assertEquals("Пароль", authPage.getPasswordPlaceholder());
    }

    @DisplayName("Chek get placeholder text in email input")
    @Story("Email input placeholder is displayed")
    @Test
    public void getAttributeInEmailInputTest() {
        authPage.getEmailPlaceholder();
        Assertions.assertEquals("E-mail", authPage.getEmailPlaceholder());
    }
    
    @DisplayName("Chek clickable close button in Login form")
    @Story("Close button in login form is clickable")
    @Test
    public void closeButtonInLoginFormTest() {
        authPage.clickCloseButton();
        Assertions.assertTrue(authPage.isModalLoginInvisible());

    }
}


