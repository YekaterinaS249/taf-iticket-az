package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecoverPasswordPage extends BasePage {

    final String INPUT_EMAIL = "//input[@id='forgot-email']";
    final String RESET_PASSWORD_BUTTON = "//button[@type='submit']";
    final String RESET_PASSWORD_TITLE = "//div[contains(@class,'font-semibold') and contains(.,'Забыли пароль')]";
    final String BACK_TO_ENTER_BUTTON = "//span[contains(normalize-space(.), 'Назад к входу')]";
    final String CLOSE_MODAL_BUTTON = "//button[@aria-label='Close modal']";
    final String INVALID_EMAIL_ERROR_MESSAGE = "//div[@id='forgot-email-hint']";
    final String INVALID_FORMAT_EMAIL_MESSAGE = "//p[contains(.,'должно быть действительным электронным адресом')]";
    final String EMAIL_REQUIRED_ERROR_MESSAGE = "//div[@id='forgot-email-hint']";
    final String RESET_PASSWORD_MESSAGE = "//p[text()='Введите email, мы отправим ссылку для сброса пароля']";
    final String MODAL_RESET_WINDOW = "//div[@id='reset-modal']";
    final String LOGIN_MODAL = "//div[@id='login-modal']";
    final String SUCCESS_SEND_EMAIL_MESSAGE = "//p[contains(@class,'notification-card__text')]";
    final String MAX_LENGTH_EMAIL_ERROR_MESSAGE = "//p[normalize-space()='Количество символов в поле e-mail адрес не может превышать 255.']";


    public RecoverPasswordPage() {
        super();
    }

    @Step("Get recover password title")
    public String getRecoverPasswordTitleTest() {
        String text = waitForVisibility(RESET_PASSWORD_TITLE).getText();
        log.info("title: {}", text);
        return text;
    }

    @Step("Enter email: {email}")
    public void setInputEmail(String email) {
       WebElement inputEmail = waitForVisibility(INPUT_EMAIL);
        log.info("User entered email: {}", email);
        inputEmail.sendKeys(email);
    }

    @Step("Click on reset password button")
    public void clickResetPasswordButton() {
        click(RESET_PASSWORD_BUTTON);
        log.info("User clicked reset password button");
    }

    @Step("Click on te back to enter button")
    public void clickBackToEnterButton() {
       click(BACK_TO_ENTER_BUTTON);
        log.info("User clicked enter button");
    }

    @Step("Click close button")
    public void clickCloseButton() {
        click(CLOSE_MODAL_BUTTON);
        log.info("User clicked close button");
    }

    @Step("Get reset password message")
    public String getResetPasswordMessage() {
        String text = waitForVisibility(RESET_PASSWORD_MESSAGE).getText();
        log.info("get reset password message: {}", text);
        return text;
    }

    @Step("Get empty email error")
    public String getEmailRequiredErrorMessage() {
        String text = waitForVisibility(EMAIL_REQUIRED_ERROR_MESSAGE).getText();
        log.info("Empty email error: {}", text);
        return text;
    }

    @Step("Get error message invalid credentials email")
    public String getInvalidEmailErrorMessage() {
        String text = waitForVisibility(INVALID_EMAIL_ERROR_MESSAGE).getText();
        log.info("error invalid email: {}", text);
        return text;
    }

    @Step("Get error message invalid format email")
    public String getInvalidEmailFormatErrorMessage() {
        String text = waitForVisibility(INVALID_FORMAT_EMAIL_MESSAGE).getText();
        log.info("error invalid format email: {}", text);
        return text;
    }

    @Step("Get success message send email")
    public String getSuccessMessageSendEmail() {
        String text = waitForVisibility(SUCCESS_SEND_EMAIL_MESSAGE).getText();
        log.info("success message send email: {}", text);
        return text;
    }

    @Step("Login modal is visible")
    public boolean isRecoverPasswordModalInvisible() {
        boolean isDisplayed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGIN_MODAL))).isDisplayed();
        log.info("login window: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Recover password modal window invisible")
    public boolean isRecoverPasswordModalWindowInvisible() {
        boolean isDisplayed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MODAL_RESET_WINDOW)));
        log.info("Recover password modal window: {}", isDisplayed);
        return isDisplayed;
    }

    @Step("Get error message for long email")
    public String getMaxLengthEmailErrorMessage() {
     String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_EMAIL_ERROR_MESSAGE))).getText();
        log.info("long email error: {}", text);
        return text;
    }

    @Step("Get email placeholder text")
    public String getEmailPlaceholderText() {
        String placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INPUT_EMAIL))).getAttribute("placeholder");
        log.info("email placeholder: {}", placeholder);
        return placeholder;
    }
}




