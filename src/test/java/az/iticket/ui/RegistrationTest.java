package az.iticket.ui;
import az.iticket.basetest.BaseTest;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RegistrationPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegistrationTest  extends BaseTest {
    private AuthPage authPage;
    private RegistrationPage registrationPage;

    @BeforeEach
    public void initRegistrationPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage();
        authPage.clickRegisterButton();
        registrationPage = new RegistrationPage();

    }
    @DisplayName("Registration user with valid data")
    @Test
    public void registrationUserWithValidData() {
        registrationPage.setFirstNameInput("Yekaterina");
        registrationPage.setLastNameInput("Kalin");
        registrationPage.setEmailInput("y22888836@gmail.com");
        registrationPage.setPhoneNumberInput("507839039");
        registrationPage.setPasswordInput("test1234");
        registrationPage.setConfirmPasswordInput("test1234");
        registrationPage.clickRegistrationButton();
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.",registrationPage.getConfrimEmailMessage());
    }





}
