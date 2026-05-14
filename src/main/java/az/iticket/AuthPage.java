package az.iticket;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage extends BasePage {
    private final String AUTH_TITLE = "//h4[text()='Войти']";
    private final String INPUT_EMAIL = "//*[@id='login-email']";
    private final String INPUT_PASSWORD = "//input[@name='password']";
    private final String SUBMIT_BUTTON = "//button[@type='submit']";
    private final String FORGOT_PASSWORD_BUTTON = "//a[@class='forgot']";
    private final String REGISTER_BUTTON = "//a[contains(text(),'Зарегистрироваться')]";
    private final String FOOTER_TITLE = "//div[@class='modal-footer']";
    private final String CLOSE_BUTTON = "(//button[@class='close'])[1]";
    private final String ERROR_MESSAGE_EMPTY_INPUT_EMAIL = "//div[contains(@class,'toastify') and contains(@aria-live,'polite')]";
    private final String ERROR_MESSAGE_EMPTY_INPUT_PASSWORD = " //div[contains(@class,'toastify') and contains(text(),'пароль')]";
    private final String LOGIN_SUCCES_MESSAGE = "//div[contains(text(),'Вы вошли')]";
    private final String INVALID_EMAIL_ERROR_MESSAGE = "//div[contains(@class,'toastify')]";
    private final String ERROR_MESSAGE_INVALID_CRENDETIALS = "//div[contains(text(),'do not match')]";
    private final String ERROR_MESSAGE_SHORT_PASSWORD = "//div[contains(text(),'пароль')]";
    private final String ERROR_MESSAGE_LONG_EMAIL = "//div[contains(text(),'Количество символов в поле e-mail адрес')]";
    private final String MODAL_OVERLAY = "(//div[contains(@class,'modal-overlay')])[last()]";


    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public String getAuthTitle() {
        return driver.findElement(By.xpath(AUTH_TITLE)).getText();
    }

    public void setInputEmail(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputEmail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(INPUT_EMAIL)));
        inputEmail.sendKeys(text);
    }

    public void setInputPassword(String text) {
        driver.findElement(By.xpath(INPUT_PASSWORD)).sendKeys(text);
    }

    public void clickSubmitButton() {
        driver.findElement(By.xpath(SUBMIT_BUTTON)).click();
    }

    public void clickForgotPasswordButton() {
        driver.findElement(By.xpath(FORGOT_PASSWORD_BUTTON)).click();
    }

    public void clickRegisterButton() {
        driver.findElement(By.xpath(REGISTER_BUTTON)).click();
    }

    public void clickCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MODAL_OVERLAY)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSE_BUTTON)));
    }

    public String getErrorMessageEmptyInputEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_EMPTY_INPUT_EMAIL)));
        return errorEmail.getText();
    }

    public String getErrorMessageEmptyInputPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_EMPTY_INPUT_PASSWORD)));
        return error.getText();
    }

    public String getLoginSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_SUCCES_MESSAGE)));
        return message.getText();
    }

    public String getFooterAuthTitle() {
        String fullText = driver.findElement(By.xpath(FOOTER_TITLE)).getText();
        return fullText.replace("Зарегистрироваться сейчас", "").trim();
    }

    public String getErrorInvalidEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement invalidEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_ERROR_MESSAGE)));
        return invalidEmail.getText();
    }

    public String getErrorMessageInvalidCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement wrongPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ERROR_MESSAGE_INVALID_CRENDETIALS)));
        return wrongPassword.getText();
    }

    public String getErrorMessageShortPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement shortPassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ERROR_MESSAGE_SHORT_PASSWORD)));
        return shortPassword.getText();
    }

    public void submitLoginFormWithEnter() {
        driver.findElement(By.xpath(INPUT_PASSWORD)).sendKeys(Keys.ENTER);
    }

    public void clearEmail() {
        driver.findElement(By.xpath(INPUT_EMAIL)).clear();

    }

    public void clearPassword() {
        driver.findElement(By.xpath(INPUT_PASSWORD)).clear();
    }

    public String getEmailValue() {
        return driver.findElement(By.xpath(INPUT_EMAIL)).getAttribute("value");
    }

    public String getPasswordValue() {
        return driver.findElement(By.xpath(INPUT_PASSWORD)).getAttribute("value");
    }

    public String getPasswordPlaceholder() {
        return driver.findElement(By.xpath(INPUT_PASSWORD)).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(By.xpath(INPUT_EMAIL)).getAttribute("placeholder");

    }

    public String getErrorMessageLongEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement longEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_MESSAGE_LONG_EMAIL)));
        return longEmail.getText();
    }

    public void waitForPageReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(driver ->
                driver.findElements(By.xpath(MODAL_OVERLAY))
        );
    }


}
