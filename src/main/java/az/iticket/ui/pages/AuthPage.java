package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthPage extends BasePage {
    final String AUTH_TITLE = "//div[text()='Войти']";
    final String INPUT_EMAIL = "//*[@id='login-email']";
    final String INPUT_PASSWORD = "//input[@id='login-password']";
    final String SUBMIT_BUTTON = "//button[@type='submit']";
    final String FORGOT_PASSWORD_BUTTON = "//button[normalize-space()='Забыли пароль?']";
    final String REGISTER_BUTTON = "//button[contains(.,'Регистрация')]";
    final String FOOTER_TITLE = "//p//span[normalize-space()='Нет аккаунта?']";
    final String CLOSE_BUTTON = "//button[@aria-label='Close modal']";
    final String INVALID_EMAIL_ERROR_MESSAGE = "//div[@id='login-email-hint']";
    final String ERROR_MESSAGE_EMPTY_INPUT_PASSWORD = "//p[contains(@class,'notification-card__text')]";
    final String PASSWORD_MIN_LENGTH_ERROR_MESSAGE = "//p[text()='Количество символов в поле пароль должно быть не меньше 8.']";
    final String INVALID_CREDENTIALS_ERROR_MESSAGE = "//p[text()='Неверное имя пользователя или пароль.']";
    final String INVALID_LENGTH_PASSWORD_ERROR_MESSAGE = "//div[@id='login-password-hint']";
    final String MAX_LENGTH_PASSWORD_ERROR_MESSAGE = "//p[text()='Количество символов в поле пароль не может превышать 255.']";
    final String USER_NOT_FOUND_MESSAGE = "//p[text()='Пользователь не найден']";
    final String ERROR_MESSAGE_LONG_EMAIL = "//div[contains(text(),'Количество символов в поле e-mail адрес')]";
    final String LOGIN_MODAL = "//div[@id='login-modal']";


    public AuthPage() {
        super();
    }

    @Step("Get auth title")
    public String getAuthTitle() {
        String authTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AUTH_TITLE))).getText();
        log.info("Auth title: {}", authTitle);
        return authTitle;
    }

    @Step("Enter email: {email}")
    public void setInputEmail(String email) {
        WebElement inputEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(INPUT_EMAIL)));
        inputEmail.sendKeys(email);
        log.info("User entered email: {}", email);
    }

    @Step("Enter password: {password}")
    public void setInputPassword(String password) {
        driver.findElement(By.xpath(INPUT_PASSWORD)).sendKeys(password);
        log.info("User entered password: {}", password);
    }

    @Step("Click submit button")
    public void clickSubmitButton() {
        driver.findElement(By.xpath(SUBMIT_BUTTON)).click();
        log.info("User clicked submit button");
    }

    @Step("Click forgot password button")
    public void clickForgotPasswordButton() {
        driver.findElement(By.xpath(FORGOT_PASSWORD_BUTTON)).click();
        log.info("User clicked forgot password button");
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        driver.findElement(By.xpath(REGISTER_BUTTON)).click();
        log.info("User clicked register button");
    }

    @Step("Click close button")
    public void clickCloseButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CLOSE_BUTTON))).click();
        log.info("User clicked close button");
    }

    @Step("Get empty email error message")
    public String getErrorMessageEmptyInputEmail() {
        WebElement errorEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_ERROR_MESSAGE)));
        String errorEmptyEmail = errorEmail.getText();
        log.info("Error message: {}", errorEmptyEmail);
        return errorEmail.getText();
    }

    @Step("Get empty password error message")
    public String getErrorMessageEmptyInputPassword() {
        String  text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_EMPTY_INPUT_PASSWORD))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get footer auth title")
    public String getFooterAuthTitle() {
        String text = driver.findElement(By.xpath(FOOTER_TITLE)).getText();
        log.info("Footer title: {}", text);
        return text;
    }

    @Step("Get error message for invalid email")
    public String getErrorInvalidEmail() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_ERROR_MESSAGE))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get error message for  min length password")
    public String getErrorPasswordMinLength() {
        String text = driver.findElement(By.xpath(PASSWORD_MIN_LENGTH_ERROR_MESSAGE)).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get error message for invalid credentials")
    public String getErrorInvalidCredentials() {
        String text = driver.findElement(By.xpath(INVALID_CREDENTIALS_ERROR_MESSAGE)).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get error message invalid length password")
    public String getErrorLengthPassword() {
        String text = driver.findElement(By.xpath(INVALID_LENGTH_PASSWORD_ERROR_MESSAGE)).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get error message max length password")
    public String getErrorPasswordMaxLength() {
        String text = driver.findElement(By.xpath(MAX_LENGTH_PASSWORD_ERROR_MESSAGE)).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Get not register user error message")
    public String getErrorMessageUserNotFound() {
        String text = driver.findElement(By.xpath(USER_NOT_FOUND_MESSAGE)).getText();
        log.info("Error message: {}", text);
        return text;
    }
    @Step("Submit login form with ENTER")
    public void submitLoginFormWithEnter() {
        driver.findElement(By.xpath(INPUT_PASSWORD)).sendKeys(Keys.ENTER);
        log.info("User submitted login form with enter");
    }

    @Step("Get placeholder for password field")
    public String getPasswordPlaceholder() {
        String placeholder = driver.findElement(By.xpath(INPUT_PASSWORD)).getAttribute("placeholder");
        log.info("Password placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder for email field")
    public String getEmailPlaceholder() {
        String placeholder = driver.findElement(By.xpath(INPUT_EMAIL)).getAttribute("placeholder");
        log.info("Email placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get error message for long email")
    public String getErrorMessageLongEmail() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_LONG_EMAIL))).getText();
        log.info("Error message: {}", text);
        return text;
    }

    @Step("Verify login modal is not visible")
    public boolean isModalLoginInvisible() {
        boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOGIN_MODAL)));
        log.info("Login modal invisible state: {}", isInvisible);
        return isInvisible;
    }
}


