package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
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
    final String AUTH_TITLE = "//h4[text()='Войти']";
    final String INPUT_EMAIL = "//*[@id='login-email']";
    final String INPUT_PASSWORD = "//input[@name='password']";
    final String SUBMIT_BUTTON = "//button[@type='submit']";
    final String FORGOT_PASSWORD_BUTTON = "//a[@class='forgot']";
    final String REGISTER_BUTTON = "//a[contains(text(),'Зарегистрироваться')]";
    final String FOOTER_TITLE = "//div[@class='modal-footer']";
    final String CLOSE_BUTTON = "//div[@id='login-modal']//button[contains(@class,'close')]";
    final String ERROR_MESSAGE_EMPTY_INPUT_EMAIL = "//div[contains(@class,'toastify') and contains(@aria-live,'polite')]";
    final String ERROR_MESSAGE_EMPTY_INPUT_PASSWORD = " //div[contains(@class,'toastify') and contains(text(),'пароль')]";
    final String LOGIN_SUCCES_MESSAGE = "//div[contains(text(),'Вы вошли')]";
    final String INVALID_EMAIL_ERROR_MESSAGE = "//div[contains(@class,'toastify')]";
    final String ERROR_MESSAGE_INVALID_CRENDETIALS = "//div[contains(text(),'do not match')]";
    final String ERROR_MESSAGE_SHORT_PASSWORD = "//div[contains(text(),'пароль')]";
    final String ERROR_MESSAGE_LONG_EMAIL = "//div[contains(text(),'Количество символов в поле e-mail адрес')]";
    final String LOGIN_MODAL = "//div[@id='login-modal']";


    public AuthPage() {
        super();
    }

    @Step("Get auth title")
    public String getAuthTitle() {
        String authTitle = driver.findElement(By.xpath(AUTH_TITLE)).getText();
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
        WebElement errorEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_EMPTY_INPUT_EMAIL)));
        String errorEmptyEmail = errorEmail.getText();
        log.info("Error message: {}", errorEmptyEmail);
        return errorEmail.getText();
    }

    @Step("Get empty password error message")
    public String getErrorMessageEmptyInputPassword() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_EMPTY_INPUT_PASSWORD)));
        String errorEmptyPassword = error.getText();
        log.info("Error message: {}", errorEmptyPassword);
        return error.getText();
    }

    @Step("Get login success message")
    public String getLoginSuccessMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_SUCCES_MESSAGE)));
        String loginSuccessMessage = message.getText();
        log.info("Login success message: {}", loginSuccessMessage);
        return message.getText();
    }

    @Step("Get footer auth title")
    public String getFooterAuthTitle() {
        String text = driver.findElement(By.xpath(FOOTER_TITLE)).getText();
        log.info("Footer title: {}", text);
        return text;
    }

    @Step("Get error message for invalid email")
    public String getErrorInvalidEmail() {
        WebElement invalidEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_ERROR_MESSAGE)));
        log.info("Error message: {}", invalidEmail.getText());
        return invalidEmail.getText();
    }

    @Step("Get invalid credentials error message")
    public String getErrorMessageInvalidCredentials() {
        WebElement wrongPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_INVALID_CRENDETIALS)));
        log.info("Error message: {}", wrongPassword.getText());
        return wrongPassword.getText();
    }

    @Step("Get error message for short password")
    public String getErrorMessageShortPassword() {
        WebElement shortPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_SHORT_PASSWORD)));
        log.info("Error message: {}", shortPassword.getText());
        return shortPassword.getText();
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
        WebElement longEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_LONG_EMAIL)));
        log.info("Error message: {}", longEmail.getText());
        return longEmail.getText();
    }

    @Step("Verify login modal is not visible")
    public boolean isModalLoginInvisible() {
        boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOGIN_MODAL)));
        log.info("Login modal invisible state: {}", isInvisible);
        return isInvisible;
    }
}


