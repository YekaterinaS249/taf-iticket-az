package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import az.iticket.ui.message.RecoverPassMessage;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RecoverPasswordPage;
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
        authPage = new AuthPage();
        recoverPasswordPage = new RecoverPasswordPage();
        authPage.clickForgotPasswordButton();
    }

    @DisplayName("Chek get title")
    @Test
    public void getTitleTest() {
        Assertions.assertEquals("Сброс пароля", recoverPasswordPage.getTitle());
    }

    @DisplayName("Chek get footer title")
    @Test
    public void footerTitleTest() {
        Assertions.assertTrue(recoverPasswordPage.getFooterText().contains("Помните пароль?"));
    }

    @DisplayName("Chek validation with valid e-mail")
    @Test
    public void validEmailTest() {
        recoverPasswordPage.setInputEmail("silantyevayekaterina@gmail.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.RESET_LINK_SENT_MESSAGE, recoverPasswordPage.getSuccessMessage());
    }

    @DisplayName("Chek validation sent long email")
    @Test
    public void longEmailTest() {
        String email = "a".repeat(64) + "@" + "b".repeat(187) + ".com";
        recoverPasswordPage.setInputEmail(email);
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.LONG_EMAIL_ERROR_MESSAGE,recoverPasswordPage.getLongEmailErrorMessage());
    }

    @DisplayName("Chek validation empty e-mail")
    @Test
    public void emptyEmailTest() {
        recoverPasswordPage.setInputEmail("");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.EMPTY_EMAIL_MESSAGE, recoverPasswordPage.getErrorMessageEmptyEmail());
    }

    @DisplayName("Chek validation e-mail without @")
    @Test
    public void invalidEmailTest() {
        recoverPasswordPage.setInputEmail("testtest.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.INVALID_EMAIL_CREDENTIALS, recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with double @")
    @Test
    public void invalidEmailTestDouble() {
        recoverPasswordPage.setInputEmail("test@@test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.INVALID_EMAIL_CREDENTIALS, recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail without domen")
    @Test
    public void invalidEmailWithoutDomen() {
        recoverPasswordPage.setInputEmail("test@");
        recoverPasswordPage.clickResetPasswordButton();
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.INVALID_EMAIL_CREDENTIALS, recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with TAB character")
    @Test
    public void invalidEmailTestTabCharacterTest() {
        recoverPasswordPage.setInputEmail("test\\t@test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.INVALID_EMAIL_CREDENTIALS, recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with new line character")
    @Test
    public void invalidEmailTestNewLineCharacterTest() {
        recoverPasswordPage.setInputEmail("test\\n@test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.INVALID_EMAIL_CREDENTIALS, recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with starting space")
    @Test
    public void invalidEmailTestStartingSpaceTest() {
        recoverPasswordPage.setInputEmail(" test@gmail.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.RESET_LINK_SENT_MESSAGE,recoverPasswordPage.getSuccessMessage());

    }

    @DisplayName("Chek validation e-mail with middle space")
    @Test
    public void invalidEmailTestMiddleSpaceTest() {
        recoverPasswordPage.setInputEmail("test @test.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.RESET_LINK_SENT_MESSAGE,recoverPasswordPage.getErrorMessageInvalidEmail());
    }

    @DisplayName("Chek validation e-mail with ending space")
    @Test
    public void invalidEmailTestEndingSpaceTest() {
        recoverPasswordPage.setInputEmail("test@test.com ");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.RESET_LINK_SENT_MESSAGE,recoverPasswordPage.getSuccessMessage());
    }

    @DisplayName("Chek validation e-mail with only spaces")
    @Test
    public void invalidEmailTestOnlySpacesTest() {
        recoverPasswordPage.setInputEmail("        ");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.EMPTY_EMAIL_MESSAGE,recoverPasswordPage.getErrorMessageEmptyEmail());
    }

    @DisplayName("Chek reset password for not register user")
    @Test
    public void resetPasswordNotRegisterUser() {
        recoverPasswordPage.setInputEmail("ecrxsrutjwlvbxfovx@vtmpj.com");
        recoverPasswordPage.clickResetPasswordButton();
        Assertions.assertEquals(RecoverPassMessage.RESET_LINK_SENT_MESSAGE, recoverPasswordPage.getSuccessMessage());
    }

    @DisplayName("Chek visible modal login window after click Enter button")
    @Test
    public void loginModalLoginWindowAfterClickEnterButton() {
        recoverPasswordPage.clickEnterButton();
        Assertions.assertTrue(recoverPasswordPage.visibleLoginWindowAfterClickEnterButton());
    }

    @DisplayName("Chek invisible reset password modal window")
    @Test
    public void invisibleResetPasswordModalWindow() {
        recoverPasswordPage.clickCloseButton();
        Assertions.assertTrue(recoverPasswordPage.isRecoverPasswordModalWindowInvisible());
    }
}