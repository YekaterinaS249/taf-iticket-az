package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
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
    private final String EMPTY_FIRST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Поле имя обязательно для заполнения')]";
    private final String LONG_FIRST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Количество символов в поле имя не может превышать 255')]";
    private final String EMPTY_LAST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Поле фамилия обязательно для заполнения')]";
    private final String LONG_LAST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Количество символов в поле фамилия не может превышать 255')]";

    public RegistrationPage() {
        super();
    }

    public String getTitle() {
        return driver.findElement(By.xpath(REGISTRATION_TITLE)).getText();
    }

    public String getFooterTitle() {
        return driver.findElement(By.xpath(REGISTRATION_FOOTER_TITTLE)).getText();
    }

    public void fillRegistrationForm(String firstName, String lastName, String phoneNumber, String email, String password, String confirmPassword) {
        log.info("Filling registration form with data: firstName = {} ,lastName = {},phoneNumber= {},email= {},password = {},confirmPassword = {}",
                firstName, lastName, phoneNumber, email, password, confirmPassword);
        type(FIRST_NAME_INPUT, firstName);
        type(LAST_NAME_INPUT, lastName);
        type(PHONE_NUMBER_INPUT, phoneNumber);
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        type(CONFIRM_PASSWORD_INPUT, confirmPassword);
        click(REGISTRATION_BUTTON);
        log.info("Clicking on the registration button");
    }

    public void type(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
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

    public String getEmptyEmailErrorMessage() {
        WebElement emptyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_FIRST_NAME_ERROR_MESSAGE)));
        return emptyInput.getText();
    }

    public String getLongFirstNameErrorMessage() {
        WebElement longCredentials = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_FIRST_NAME_ERROR_MESSAGE)));
        return longCredentials.getText();
    }

    public String getEmptyLastNameErrorMessage() {
        return driver.findElement(By.xpath(EMPTY_LAST_NAME_ERROR_MESSAGE)).getText();
    }

    public String getLongLastNameErrorMessage() {
       WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_LAST_NAME_ERROR_MESSAGE)));
       return lastName.getText();
    }

}