package az.iticket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage extends BasePage {

    private final String INPUT_EMAIL = "(//input[@name='email'])[2]";
    private final String RESET_PASSWORD_BUTTON = "//button[contains(text(),'Сброс')]";
    private final String RESET_PASSWORD_TITLE = "/h4[normalize-space()='Сброс пароля']";
    private final String RESET_FOOTER_TITLE = "//div[contains(@class,'modal-footer') and contains(.,'Помните пароль')]";
    private final String ENTER_BUTTON = "//div[contains(@class,'modal-footer')]//a[normalize-space()='Войти']";
    private final String CLOSE_BUTTON = "(//button[@class='close'])[2]";

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
        return driver.findElement(By.xpath(RESET_FOOTER_TITLE)).getText();
    }

    public void clickEnterButton() {
        driver.findElement(By.xpath(ENTER_BUTTON)).click();
    }

    public void clickCloseButton() {
        driver.findElement(By.xpath(CLOSE_BUTTON)).click();
    }
}

