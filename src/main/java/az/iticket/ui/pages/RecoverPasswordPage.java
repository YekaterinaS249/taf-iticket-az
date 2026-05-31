package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecoverPasswordPage extends BasePage {

    private final String INPUT_EMAIL = "(//input[@name='email'])[2]";
    private final String RESET_PASSWORD_BUTTON = "//button[contains(text(),'Сброс')]";
    private final String RESET_PASSWORD_TITLE = "//h4[contains(text(),'Сброс пароля')]";
    private final String RESET_FOOTER_TITLE = "//div[contains(@class,'modal-footer') and contains(.,'Помните пароль')]";
    private final String ENTER_BUTTON = "//div[contains(@class,'modal-footer')]//a[normalize-space()='Войти']";
    private final String CLOSE_BUTTON = "//div[@id='reset-password-modal']//button[contains(@class,'close')]";
    private final String EMPTY_INPUT_EMAIL_MESSAGE = "//*[@aria-live='polite' and contains(.,'обязательно')]";
    private final String INVALID_EMAIL_MESSAGE = "//div[@aria-live='polite' and contains(text(),'e-mail')]";
    private final String SUCCESS_MESSAGE = "//div[contains(text(),'Ссылка на сброс пароля была отправлена')]";
    private final String MODAL_RESET_WINDOW = "//div[@id='reset-password-modal']";
    private final String LOGIN_MODAL = "//div[@id='login-modal']";
    private final String LONG_EMAIL_ERROR_MESSAGE = "//div[contains(text(),'Количество символов в поле e-mail адрес не может превышать 255')]";



    public RecoverPasswordPage() {
        super();
    }

    public String getTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESET_PASSWORD_TITLE)));
        return title.getText();
    }

    public void setInputEmail(String email) {
        driver.findElement(By.xpath(INPUT_EMAIL)).sendKeys(email);
    }

    public void clickResetPasswordButton() {
        driver.findElement(By.xpath(RESET_PASSWORD_BUTTON)).click();
    }

    public String getFooterText() {
        return driver.findElement(By.xpath(RESET_FOOTER_TITLE)).getText().trim();
    }

    public void clickEnterButton() {
        driver.findElement(By.xpath(ENTER_BUTTON)).click();
    }

    public void clickCloseButton() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSE_BUTTON)));
        closeButton.click();
    }

    public String getErrorMessageEmptyEmail() {
        WebElement errorEmptyEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_INPUT_EMAIL_MESSAGE)));
        return errorEmptyEmail.getText();
    }

    public String getErrorMessageInvalidEmail() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_MESSAGE)));
        return errorMessage.getText();
    }

    public String getSuccessMessage() {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_EMAIL_ERROR_MESSAGE)));
        return successMessage.getText();
    }

    public boolean visibleLoginWindowAfterClickEnterButton() {
        WebElement loginWindow = driver.findElement(By.xpath(LOGIN_MODAL));
        return loginWindow.isDisplayed();
    }

    public boolean isRecoverPasswordModalWindowInvisible() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MODAL_RESET_WINDOW)));
    }

    public String getLongEmailErrorMessage() {
        WebElement longEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_MESSAGE)));
        return longEmail.getText();
    }
}

