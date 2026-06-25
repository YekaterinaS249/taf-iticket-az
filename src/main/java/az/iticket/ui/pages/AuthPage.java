package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthPage extends BasePage {
    final String AUTH_MODAL_TITLE = "//div[text()='Войти']";
    final String INPUT_EMAIL = "//*[@id='login-email']";
    final String INPUT_PASSWORD = "//input[@id='login-password']";
    final String LOGIN_BUTTON = "//button[@type='submit']";
    final String FORGOT_PASSWORD_BUTTON = "//button[normalize-space()='Забыли пароль?']";
    final String REGISTRATION_BUTTON = "//button[.//span[text()='Регистрация']]";
    final String LOGIN_FOOTER_TITLE = "//p//span[normalize-space()='Нет аккаунта?']";
    final String MODAL_CLOSE_BUTTON = "//button[@aria-label='Close modal']";
    final String INVALID_EMAIL_ERROR_MESSAGE = "//div[@id='login-email-hint']";
    final String EMPTY_PASSWORD_ERROR_MESSAGE = "//p[contains(text(),'Поле пароль обязательно')]";
    final String PASSWORD_SHORT_ERROR_MESSAGE = "//p[text()='Количество символов в поле пароль должно быть не меньше 8.']";
    final String INVALID_CREDENTIALS_ERROR_MESSAGE = "//p[text()='Неверное имя пользователя или пароль.']";
    final String PASSWORD_INVALID_FORMAT_ERROR_MESSAGE = "//div[@id='login-password-hint']";
    final String MAX_LENGTH_PASSWORD_ERROR_MESSAGE = "//p[text()='Количество символов в поле пароль не может превышать 255.']";
    final String USER_NOT_FOUND_MESSAGE = "//p[text()='Пользователь не найден']";
    final String MAX_LENGTH_EMAIL_ERROR_MESSAGE = "//p[text()='Количество символов в поле e-mail адрес не может превышать 255.']";
    final String INVALID_EMAIL_FORMAT_MESSAGE = "//p[contains(text(),'Поле e-mail адрес должно быть действительным электронным адресом')]";
    final String PASSWORD_TOGGLE_BUTTON = "//button[@aria-label='Toggle password visibility']";
    final String LOGIN_MODAL = "//div[@id='login-modal']";


    public AuthPage() {
        super();
    }

    @Step("Get authentication modal title")
    public String getAuthTitle() {
        String authTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AUTH_MODAL_TITLE))).getText();
        log.info("Auth title: {}", authTitle);
        return authTitle;
    }

    @Step("Enter email '{email}'")
    public void setInputEmail(String email) {
        WebElement inputEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(INPUT_EMAIL)));
        inputEmail.sendKeys(email);
        log.info("User entered email: {}", email);
    }

    @Step("Enter password '{password}'")
    public void setInputPassword(String password) {
        WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(INPUT_PASSWORD)));
        inputPassword.sendKeys(password);
        log.info("User entered password: {}", password);
    }

    @Step("Click login button")
    public void clickSubmitButton() {
        driver.findElement(By.xpath(LOGIN_BUTTON)).click();
        log.info("User clicked submit button");
    }

    @Step("Click 'Forgot password' button")
    public void clickForgotPasswordButton() {
        WebElement forgotPasswordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FORGOT_PASSWORD_BUTTON)));
        log.info("User clicked forgot password button");
        forgotPasswordButton.click();
    }

    @Step("Click registration button")
    public void clickRegisterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(REGISTRATION_BUTTON)));
        log.info("User clicked registration button");
        button.click();
    }

    @Step("Click modal close button")
    public void clickCloseButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MODAL_CLOSE_BUTTON))).click();
        log.info("User clicked close button");
    }

    @Step("Get email required error message")
    public String getErrorMessageEmptyInputEmail() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_ERROR_MESSAGE))).getText();
        log.info("Error message: {}",text);
        return text;
    }

    @Step("GGet invalid email format error message")
    public String getErrorMessageInvalidEmailFormat() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_FORMAT_MESSAGE))).getText();
        log.info("Error message: {}",text);
        return text;
    }

    @Step("Get password required error message")
    public String getErrorMessageEmptyInputPassword() {
        String  text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_PASSWORD_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get authentication footer title")
    public String getFooterAuthTitle() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_FOOTER_TITLE))).getText();
        log.info("Footer title: {}", text);
        return text;
    }

    @Step("Get invalid email error message")
    public String getErrorInvalidEmail() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("et password minimum length error message")
    public String getErrorShortPassword() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_SHORT_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get invalid credentials error message")
    public String getErrorInvalidCredentials() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_CREDENTIALS_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get password format error message")
    public String getErrorLengthPassword() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_INVALID_FORMAT_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get password maximum length error message")
    public String getErrorPasswordMaxLength() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_PASSWORD_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get user not found error message")
    public String getErrorMessageUserNotFound() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USER_NOT_FOUND_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get email input placeholder")
    public String getEmailPlaceholder() {
        String placeholder =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INPUT_EMAIL))).getAttribute("placeholder");
        log.info("Email placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get email maximum length error message")
    public String getErrorMessageMaxLengthEmail() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_EMAIL_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Verify login modal is closed")
    public boolean isModalLoginInvisible() {
        boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOGIN_MODAL)));
        log.info("Login modal invisible state: {}", isInvisible);
        return isInvisible;
    }

    @Step("Verify password validation error disappears when password is valid")
    public boolean isPasswordValidationErrorNotDisplayed() {
       boolean isNotDisplayed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(PASSWORD_INVALID_FORMAT_ERROR_MESSAGE)));
       log.info("Error message: {}", isNotDisplayed);
       return isNotDisplayed;
    }

    @Step("Click toggle password visibility button")
    public void clickTogglePasswordButton() {
        driver.findElement(By.xpath(PASSWORD_TOGGLE_BUTTON)).click();
    }

    @Step("Get password field input type")
    public String getPasswordFieldType() {
        return driver.findElement(By.xpath(INPUT_PASSWORD)).getAttribute("type");
    }
}


