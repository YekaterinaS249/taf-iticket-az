package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecoverPasswordTest  extends BaseTest {
    private AuthPage authPage;
    private RecoverPasswordPage recoverPasswordPage;

    @BeforeEach
    public void initRecoverPasswordPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
    }

    @DisplayName("Chek get title")
    @Test
    public void getTitleTest() {
        authPage.clickForgotPasswordButton();
        Assertions.assertEquals("Сброс пароля", recoverPasswordPage.getTitle());
    }

    @DisplayName("Chek get footer title")
    @Test
    public void footerTitleTest() {
        authPage.clickForgotPasswordButton();
        Assertions.assertTrue(recoverPasswordPage.getFooterText().contains("Помните пароль?"));
    }

    @DisplayName("Chek validation with valid e-mail")
    @Test
    public void validEmailTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("silantyevayekaterina@gmail.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Ссылка на сброс пароля была отправлена!", recoverPasswordPage.getSuccessMessage());
    }

    @DisplayName("Chek validation empty e-mail")
    @Test
    public void emptyEmailTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес обязательно для заполнения.", recoverPasswordPage.getErrorMessageEmptyEmail());
    }

    @DisplayName("Chek validation e-mail without @")
    @Test
    public void invalidEmailTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("testtest.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес должно быть действительным электронным адресом.", recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with double @")
    @Test
    public void invalidEmailTestDouble() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("test@@test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес должно быть действительным электронным адресом.", recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail without domen")
    @Test
    public void invalidEmailTestDomen() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("test@");
        recoverPasswordPage.clickResetPasswordButton();
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес должно быть действительным электронным адресом.", recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with TAB character")
    @Test
    public void invalidEmailTestTabCharacterTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("test\\t@test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес должно быть действительным электронным адресом.", recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with new line character")
    @Test
    public void invalidEmailTestNewLineCharacterTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("test\\n@test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес должно быть действительным электронным адресом.", recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with starting space")
    @Test
    public void invalidEmailTestStartingSpaceTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail(" test@gmail.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Ссылка на сброс пароля была отправлена!",recoverPasswordPage.getSuccessMessage());

    }

    @DisplayName("Chek validation e-mail with middle space")
    @Test
    public void invalidEmailTestMiddleSpaceTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("test @test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес должно быть действительным электронным адресом.",recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with ending space")
    @Test
    public void invalidEmailTestEndingSpaceTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("test@test.com ");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Ссылка на сброс пароля была отправлена!",recoverPasswordPage.getSuccessMessage());
    }

    @DisplayName("Chek validation e-mail with only spaces")
    @Test
    public void invalidEmailTestOnlySpacesTest() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("        ");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Поле e-mail адрес обязательно для заполнения.",recoverPasswordPage.getErrorMessageEmptyEmail());
    }

    @DisplayName("Chek reset password for not register user")
    @Test
    public void resetPasswordNotRegisterUser() {
        authPage.clickForgotPasswordButton();
        recoverPasswordPage.setInputEmail("ecrxsrutjwlvbxfovx@vtmpj.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals("Ссылка на сброс пароля была отправлена!", recoverPasswordPage.getSuccessMessage());
    }
}