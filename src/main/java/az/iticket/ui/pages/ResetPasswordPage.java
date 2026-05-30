package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import org.openqa.selenium.By;

public class ResetPasswordPage extends BasePage {
    private final String MODAL_TITLE = "//h4[text()='Сброс пароля']";
    private final String RESET_PASSWORD_INPUT = "(//input[@name='password'])[3]";
    private final String RESET_PASSWORD_CONFIRM = "//input[@name='password_confirmation']";
    private final String RESET_BUTTON = "//button[normalize-space()='Сброс']";
    // private final String FOOTER_MODAL_TITLE = "//div[contains(text(),'Помните пароль?')]";
    // private final String CLOSE_BUTTON = "(//button[@class='close'])[5]";
    //  private final String ENTER_BUTTON ="//a[text()='Войти']";

    public ResetPasswordPage() {
        super();
    }

    public void setResetPasswordInputText(String text) {
        driver.findElement(By.xpath(RESET_PASSWORD_INPUT)).sendKeys(text);
    }

    public void setResetPasswordConfirmationText(String text) {
        driver.findElement(By.xpath(RESET_PASSWORD_CONFIRM)).sendKeys(text);
    }

    public void clickResetButton() {
        driver.findElement(By.xpath(RESET_BUTTON)).click();
    }

}

