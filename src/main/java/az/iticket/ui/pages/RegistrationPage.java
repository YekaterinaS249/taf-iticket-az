package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationPage extends BasePage {
    private final String FIRST_NAME_INPUT = "//input[@name='first_name']";
    private final String LAST_NAME_INPUT = "//input[@name='last_name']";
    private final String PHONE_NUMBER_INPUT = "//input[@placeholder='Мобильный']";
    private final String EMAIL_INPUT = "//div[@class='form-group mt-4']//input[@name='email']";
    private final String PASSWORD_INPUT = "(//input[@type='password' and @name='password'])[2]";
    private final String CONFIRM_PASSWORD_INPUT = "//input[@name='confirm_password']";
    private final String REGISTRATION_BUTTON = "//button[@type='button' and contains(text(),'Регистрация')]";
    private final String REGISTRATION_TITLE = "//h4[text()='Регистрация']";
    private final String REGISTRATION_FOOTER_TITTLE = "//div[contains(text(),'Уже зарегистрирован')]";
    private final String LOGIN_LINK = "//a[normalize-space()='Войдите здесь']";
    private final String CLOSE_BUTTON = "//h4[text()='Регистрация']/ancestor::div[contains(@class,'modal-dialog')]//button[contains(@class,'close')]";
    private final String CONFRIM_EMAIL = "//div[contains(text(),'подтвердите e-mail')]";

    public RegistrationPage() {
        super();
    }

    public String getTitle() {
        return driver.findElement(By.xpath(REGISTRATION_TITLE)).getText();
    }

    public String getFooterTitle() {
        return driver.findElement(By.xpath(REGISTRATION_FOOTER_TITTLE)).getText();
    }

    public void setFirstNameInput(String firstName) {
        driver.findElement(By.xpath(FIRST_NAME_INPUT)).sendKeys(firstName);
    }

    public void setLastNameInput(String lastName) {
        driver.findElement(By.xpath(LAST_NAME_INPUT)).sendKeys(lastName);
    }

    public void setPhoneNumberInput(String phoneNumber) {
        driver.findElement(By.xpath(PHONE_NUMBER_INPUT)).sendKeys(phoneNumber);
    }

    public void setEmailInput(String email) {
        driver.findElement(By.xpath(EMAIL_INPUT)).sendKeys(email);
    }

    public void setPasswordInput(String password) {
        driver.findElement(By.xpath(PASSWORD_INPUT)).sendKeys(password);
    }

    public void setConfirmPasswordInput(String confirmPassword) {
        driver.findElement(By.xpath(CONFIRM_PASSWORD_INPUT)).sendKeys(confirmPassword);
    }

    public void clickRegistrationButton() {
        driver.findElement(By.xpath(REGISTRATION_BUTTON)).click();
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath(LOGIN_LINK)).click();
    }

    public void clickCloseButton() {
        driver.findElement(By.xpath(CLOSE_BUTTON)).click();
    }

    public String getConfrimEmailMessage() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONFRIM_EMAIL)));
        return email.getText();
    }

}