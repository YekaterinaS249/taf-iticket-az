package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public RegistrationPage() {
        super();
    }

    public String getTitle() {
        return driver.findElement(By.xpath(REGISTRATION_TITLE)).getText();
    }

    public String getFooterTitle() {
        return driver.findElement(By.xpath(REGISTRATION_FOOTER_TITTLE)).getText();
    }
}