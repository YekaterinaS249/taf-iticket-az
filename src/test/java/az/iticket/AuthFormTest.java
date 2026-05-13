package az.iticket;

import az.iticket.constant.LoginMessage;
import org.junit.jupiter.api.*;

public class AuthFormTest extends BaseTest {
    private AuthPage authPage;

    @BeforeEach
    public void initAuthPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage(driver);
    }

    @DisplayName(" Chek Log in with valid data")
    @Test
    public void loginWithValidCredentialsTest() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("aska1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals("Вы вошли в систему", authPage.getLoginSuccessMessage());
    }

    @DisplayName("Chek submit login form with Enter button")
    @Test
    public void submitLoginFormWithEnter() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("aska1234");
        authPage.submitLoginFormWithEnter();
        Assertions.assertEquals("Вы вошли в систему", authPage.getLoginSuccessMessage());
    }

    @DisplayName("Chek submit empty e-mail")
    @Test
    public void loginWithEmptyEmailTest() {
        authPage.setInputEmail("");
        authPage.setInputPassword("aska1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_EMAIL, authPage.getErrorMessageEmptyInputEmail());
    }

    @DisplayName("Chek submit empty password")
    @Test
    public void loginWithEmptyPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_PASSWORD, authPage.getErrorMessageEmptyInputPassword());
    }

    @DisplayName("Chek login with invalid e-mail(without @)")
    @Test
    public void loginWithInvalidEmailTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkzvtmpj.com");
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_EMAIL_FORMAT, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Chek login with wrong e-mail")
    @Test
    public void loginWithWrongEmailTest() {
        authPage.setInputEmail("thsmklqhnxyuqzfngl@jbsze.com");
        authPage.setInputPassword("aska1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Chek login with e-mail only spaces")
    @Test
    public void loginWithEmailOnlySpacesTest() {
        authPage.setInputEmail("            ");
        authPage.setInputPassword("test1234");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_EMAIL, authPage.getErrorInvalidEmail());
    }

    @DisplayName("Chek clear input email and submit form")
    @Test
    public void clearInputEmailTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("test1234");
        authPage.clearEmail();
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_EMAIL, authPage.getErrorMessageEmptyInputEmail());

    }

    @DisplayName("Chek login with wrong password")
    @Test
    public void loginWithWrongPasswordTest() {
        authPage.setInputEmail("silantyevakatya1@gmail.com");
        authPage.setInputPassword("12345678");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorMessageInvalidCredentials());
    }

    @DisplayName("Chek login with short password")
    @Test
    public void loginWithShortPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("t");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.PASSWORD_TOO_SHORT, authPage.getErrorMessageShortPassword());
    }

    @DisplayName("Chek login test with invalid password length")
    @Test
    public void loginWithInvalidPasswordLengthTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("test123");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.PASSWORD_TOO_SHORT, authPage.getErrorMessageShortPassword());
    }

    @DisplayName("Chek login with long password")
    @Test
    public void loginWithLongPasswordTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("test12345");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorMessageInvalidCredentials());
    }

    @Test
    public void loginWithPasswordOnlySpacesTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.setInputPassword("        ");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.ERROR_EMPTY_PASSWORD, authPage.getErrorMessageEmptyInputPassword());

    }

    @DisplayName("Chek get auth title")
    @Test
    public void getAuthTitleTest() {
        Assertions.assertEquals("Войти", authPage.getAuthTitle());
    }

    @DisplayName("Chek get footer auth title")
    @Test
    public void getFooterAuthTitleTest() {
        Assertions.assertEquals("Впервые на iTicket.AZ?", authPage.getFooterAuthTitle());
    }

    @DisplayName("Chek login not register user")
    @Test
    public void loginNotRegisterUserTest() {
        authPage.setInputEmail("katyatest97@gmail.com");
        authPage.setInputPassword("katya199");
        authPage.clickSubmitButton();
        Assertions.assertEquals(LoginMessage.INVALID_CREDENTIALS, authPage.getErrorMessageInvalidCredentials());
    }

    @DisplayName("Chek clear input email")
    @Test
    public void clearEmailInputTest() {
        authPage.setInputEmail("cqqgslqadspnhazzkz@vtmpj.com");
        authPage.clearEmail();
        Assertions.assertEquals("", authPage.getEmailValue());
    }

    @DisplayName("Chek clear input password")
    @Test
    public void clearPasswordInputTest() {
        authPage.setInputPassword("aska1234");
        authPage.clearPassword();
        Assertions.assertEquals("", authPage.getPasswordValue());
    }

    @DisplayName("Chek get placeholder text in password input")
    @Test
    public void getAttributeInPasswordInputTest() {
        authPage.getPasswordPlaceholder();
        Assertions.assertEquals("Пароль", authPage.getPasswordPlaceholder());
    }

    @DisplayName("Chek get placeholder text in email input")
    @Test
    public void getAttributeInEmailInputTest() {
        authPage.getEmailPlaceholder();
        Assertions.assertEquals("E-mail", authPage.getEmailPlaceholder());
    }
    @DisplayName("Chek clickable close button in Login form")
    @Test
    public void closeButtonInLoginFormTest() {
        homePage.clickAuthButton();
        authPage.clickCloseButton();
    }
}

