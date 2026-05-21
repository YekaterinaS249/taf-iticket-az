package az.iticket.ui;

import az.iticket.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage extends BasePage {

    private final String INPUT_EMAIL = "(//input[@name='email'])[2]";
    private final String RESET_PASSWORD_BUTTON = "//button[contains(text(),'Сброс')]";
    private final String RESET_PASSWORD_TITLE = "//h4[contains(text(),'Сброс пароля')]";
    private final String RESET_FOOTER_TITLE = "//div[contains(@class,'modal-footer') and contains(.,'Помните пароль')]";
    private final String RESET_BUTTON = "//div[contains(@class,'modal-footer')]//a[normalize-space()='Войти']";
    private final String CLOSE_BUTTON = "(//button[@class='close'])[2]";
    private final String EMPTY_INPUT_EMAIL_MESSAGE = "//*[@aria-live='polite' and contains(.,'обязательно')]";
    private final String INVALID_EMAIL_MESSAGE = "//div[@aria-live='polite' and contains(text(),'e-mail')]";
    private final String SUCCESS_MESSAGE = "//div[contains(text(),'Ссылка на сброс пароля была отправлена')]";


    public RecoverPasswordPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle() {
        return driver.findElement(By.xpath(RESET_PASSWORD_TITLE)).getText();
    }

    public void setInputEmail(String text) {
        driver.findElement(By.xpath(INPUT_EMAIL)).sendKeys(text);
    }

    public void clickResetPasswordButton() {
        driver.findElement(By.xpath(RESET_PASSWORD_BUTTON)).click();
    }

    public String getFooterText() {
        return driver.findElement(By.xpath(RESET_FOOTER_TITLE)).getText().trim();
    }

    public void clickEnterButton() {
        driver.findElement(By.xpath(RESET_BUTTON)).click();
    }

    public void clickCloseButton() {
        driver.findElement(By.xpath(CLOSE_BUTTON)).click();
    }

    public String getErrorMessageEmptyEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorEmptyEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_INPUT_EMAIL_MESSAGE)));
        return errorEmptyEmail.getText();
    }
    public String getErrorMessageInvalidEmail() {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_MESSAGE)));
       return errorMessage.getText();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUCCESS_MESSAGE)));
        return successMessage.getText();
    }
}

