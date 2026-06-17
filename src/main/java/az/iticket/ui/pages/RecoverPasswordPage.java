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

    final String INPUT_EMAIL = "(//input[@name='email'])[2]";
    final String RESET_PASSWORD_BUTTON = "//button[contains(text(),'Сброс')]";
    final String RESET_PASSWORD_TITLE = "//h4[contains(text(),'Сброс пароля')]";
    final String RESET_FOOTER_TITLE = "//div[contains(@class,'modal-footer') and contains(.,'Помните пароль')]";
    final String ENTER_BUTTON = "//div[contains(@class,'modal-footer')]//a[normalize-space()='Войти']";
    final String CLOSE_BUTTON = "//div[@id='reset-password-modal']//button[contains(@class,'close')]";
    final String EMPTY_INPUT_EMAIL_MESSAGE = "//*[@aria-live='polite' and contains(.,'обязательно')]";
    final String INVALID_EMAIL_MESSAGE = "//div[@aria-live='polite' and contains(text(),'e-mail')]";
    final String SUCCESS_MESSAGE = "//div[contains(text(),'Ссылка на сброс пароля была отправлена')]";
    final String MODAL_RESET_WINDOW = "//div[@id='reset-password-modal']";
    final String LOGIN_MODAL = "//div[@id='login-modal']";
    final String LONG_EMAIL_ERROR_MESSAGE = "//div[contains(text(),'Количество символов в поле e-mail адрес не может превышать 255')]";


    public RecoverPasswordPage() {
        super();
    }

    @Step("Get recover password  title")
    public String getTitle() {
        String titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESET_PASSWORD_TITLE))).getText();
        log.info("title: {}", titleText);
        return titleText;
    }

    @Step("Enter email: {email}")
    public void setInputEmail(String email) {
        driver.findElement(By.xpath(INPUT_EMAIL)).sendKeys(email);
        log.info("User entered email: {}", email);
    }

    @Step("Click on reset password button")
    public void clickResetPasswordButton() {
        driver.findElement(By.xpath(RESET_PASSWORD_BUTTON)).click();
        log.info("User clicked reset password button");
    }

    @Step("Get footer text for recover password page")
    public String getFooterText() {
        String footerText = driver.findElement(By.xpath(RESET_FOOTER_TITLE)).getText().trim();
        log.info("footer text: {}", footerText);
        return footerText;
    }

    @Step("Click Enter button")
    public void clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ENTER_BUTTON))).click();
        log.info("User clicked enter button");
    }

    @Step("Click close button")
    public void clickCloseButton() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSE_BUTTON)));
        log.info("User clicked close button");
        closeButton.click();
    }

    @Step("Get error message for empty email")
    public String getErrorMessageEmptyEmail() {
        String errorEmptyEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_INPUT_EMAIL_MESSAGE))).getText();
        log.info("error empty email: {}", errorEmptyEmail);
        return errorEmptyEmail;
    }

    @Step("Get error message invalid credentials email")
    public String getErrorMessageInvalidEmail() {
        String errorMessageInvalidEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_MESSAGE))).getText();
        log.info("error invalid email: {}", errorMessageInvalidEmail);
        return errorMessageInvalidEmail;
    }

    @Step("Get succes message")
    public String getSuccessMessage() {
         String successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUCCESS_MESSAGE))).getText();
         log.info("success message: {}", successMessage);
         return successMessage;
    }

    @Step("Login modal is visible")
    public boolean visibleLoginWindowAfterClickEnterButton() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL))).isDisplayed();
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
    public String getLongEmailErrorMessage() {
     String longEmailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_EMAIL_ERROR_MESSAGE))).getText();
        log.info("long email error: {}", longEmailError);
        return longEmailError;
    }
}


