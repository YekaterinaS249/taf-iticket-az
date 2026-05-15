package az.iticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecoverPasswordTest  extends BaseTest{
    private AuthPage authPage;
    private RecoverPasswordPage recoverPasswordPage;

    @BeforeEach
    public void initRecoverPasswordPage(){
        homePage.clickAuthButton();
        authPage = new AuthPage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
    }

    @DisplayName("Chek get title")
    @Test
    public void GetTitleTest(){
        authPage.clickForgotPasswordButton();
        Assertions.assertEquals("Сброс пароля", recoverPasswordPage.getTitle());
    }



}
