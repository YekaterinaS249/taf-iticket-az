package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationPage extends BasePage {
    final String FIRST_NAME_INPUT = "//input[@name='first_name']";
    final String LAST_NAME_INPUT = "//input[@name='last_name']";
    final String PHONE_NUMBER_INPUT = "//input[@placeholder='Мобильный']";
    final String EMAIL_INPUT = "//div[@class='form-group mt-4']//input[@name='email']";
    final String PASSWORD_INPUT = "(//input[@type='password' and @name='password'])[2]";
    final String CONFIRM_PASSWORD_INPUT = "//input[@name='confirm_password']";
    final String REGISTRATION_BUTTON = "//button[@type='button' and contains(text(),'Регистрация')]";
    final String REGISTRATION_TITLE = "//h4[text()='Регистрация']";
    final String REGISTRATION_FOOTER_TITTLE = "//div[contains(text(),'Уже зарегистрирован')]";
    final String LOGIN_LINK = "//a[normalize-space()='Войдите здесь']";
    final String CLOSE_BUTTON = "//h4[text()='Регистрация']/ancestor::div[contains(@class,'modal-dialog')]//button[contains(@class,'close')]";
    final String CONFRIM_EMAIL = "//div[contains(text(),'подтвердите e-mail')]";
    final String EMPTY_FIRST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Поле имя обязательно для заполнения')]";
    final String LONG_FIRST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Количество символов в поле имя не может превышать 255')]";
    final String EMPTY_LAST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Поле фамилия обязательно для заполнения')]";
    final String LONG_LAST_NAME_ERROR_MESSAGE = "//div[contains(text(),'Количество символов в поле фамилия не может превышать 255')]";
    final String EMPTY_PHONE_NUMBER_ERROR_MESSAGE = "//div[contains(text(),'Поле моб. номер обязательно')]";
    final String LONG_PHONE_NUMBER_ERROR_MESSAGE = "//div[contains(.,'Количество символов в поле моб. номер')]";
    final String EMPTY_EMAIL_INPUT_ERROR_MESSAGE = "//div[text()='Поле e-mail адрес обязательно для заполнения.']";
    final String EMAIL_ALREADY_EXITS_ERROR_MESSAGE = "//div[text()='Такое значение поля e-mail адрес уже существует.']";
    final String INVALID_EMAIL_CDERENTIALS_ERROR_MESSAGE = "//div[text()='Поле e-mail адрес должно быть действительным электронным адресом.']";
    final String EMPTY_PASSWORD_ERROR_MESSAGE = "//div[@aria-live='polite' and text()='Поле пароль обязательно для заполнения.']";
    final String SHORT_PASSWORD_ERROR_MESSAGE = "//div[@aria-live='polite' and text()='Количество символов в поле пароль должно быть не меньше 8.']";
    final String LONG_PASSWORD_ERROR_MESSAGE = "//div[@aria-live='polite' and text()='Количество символов в поле пароль не может превышать 255.']";
    final String LOGIN_MODAL = "//div[@id='login-modal']";
    final String PHONE_DROPDOWN = "//span[contains(@class,'vti__selection')]";

    public RegistrationPage() {
        super();
    }

    @Step("Get registration page title")
    public String getTitle() {
        String text = driver.findElement(By.xpath(REGISTRATION_TITLE)).getText();
        log.info("Title: {}", text);
        return text;
    }

    @Step("Get registration page footer title")
    public String getFooterTitle() {
        String text = driver.findElement(By.xpath(REGISTRATION_FOOTER_TITTLE)).getText();
        String footerTitle = text.substring(0, text.indexOf("?") + 1);
        log.info("Footer title: {}", footerTitle);
        return footerTitle;
    }

    @Step("User fills registration form and submit it")
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

    @Step("User click on the login link")
    public void clickLoginButton() {
        log.info("Clicking on the login button");
        driver.findElement(By.xpath(LOGIN_LINK)).click();
    }

    @Step("User click on the close button")
    public void clickCloseButton() {
        log.info("Clicking on the close button");
        driver.findElement(By.xpath(CLOSE_BUTTON)).click();
    }

    @Step("Get confirm message")
    public String getConfirmEmailMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONFRIM_EMAIL))).getText();
        log.info("Confirm email message: {}", text);
        return text;
    }

    @Step("Get empty email error message")
    public String getEmptyEmailErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_FIRST_NAME_ERROR_MESSAGE))).getText();
        log.info("Empty email message: {}", text);
        return text;
    }

    @Step("Get long email error message")
    public String getLongFirstNameErrorMessage() {
       String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_FIRST_NAME_ERROR_MESSAGE))).getText();
       log.info("Long email error message: {}", text);
        return text;
    }

    @Step("Get empty last name error message")
    public String getEmptyLastNameErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_LAST_NAME_ERROR_MESSAGE))).getText();
        log.info("Empty last name error message: {}", text);
        return text;
    }

    @Step("Get long last name error message")
    public String getLongLastNameErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_LAST_NAME_ERROR_MESSAGE))).getText();
        log.info("Long email error message: {}", text);
        return text;
    }

    @Step("Get empty phone error message")
    public String getEmptyPhoneNumberErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_PHONE_NUMBER_ERROR_MESSAGE))).getText();
        log.info("Empty phone number error message: {}", text);
        return text;
    }

    @Step("Get long phone number error message")
    public String getErrorMessageLongPhoneNumber() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_PHONE_NUMBER_ERROR_MESSAGE))).getText();
        log.info("Long email error message: {}", text);
        return text;
    }

    @Step("Get empty email error message")
    public String getErrorMessageEmptyEmail() {
       String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_EMAIL_INPUT_ERROR_MESSAGE))).getText();
       log.info("Empty email error message: {}", text);
        return text;
    }

    @Step("Get user already exits error message")
    public String getErrorMessageEmailAlreadyExists() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALREADY_EXITS_ERROR_MESSAGE))).getText();
        log.info("Email already exists error message: {}", text);
        return text;
    }

    @Step("Get invalid credentials error message")
    public String getInvalidEmailCredentialsErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_CDERENTIALS_ERROR_MESSAGE))).getText();
        log.info("Invalid email credentials error message: {}", text);
        return text;
    }

    @Step("Get empty password error message")
    public String getEmptyPasswordErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_PASSWORD_ERROR_MESSAGE))).getText();
        log.info("Empty password error message: {}", text);
        return text;
    }

    @Step("Get short password error message")
    public String getShortPasswordErrorMessage() {
       String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SHORT_PASSWORD_ERROR_MESSAGE))).getText();
       log.info("Short password error message: {}", text);
        return text;
    }

    @Step("Get error long password error message")
    public String getErrorMessageLongPassword() {
        String text= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LONG_PASSWORD_ERROR_MESSAGE))).getText();
        log.info("Long email error message: {}", text);
        return text;
    }

    @Step("Login modal is displayed: {result}")
    public boolean isModalLoginDisplayed() {
        log.info("Checking  modal login is displayed");
        return driver.findElement(By.xpath(LOGIN_MODAL)).isDisplayed();
    }

    @Step("Get placeholder text in first name")
    public String getPlaceholderFirstNameInputText() {
        String placeholder = driver.findElement(By.xpath(FIRST_NAME_INPUT)).getAttribute("placeholder");
        log.info("First name placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in last name")
    public String getPlaceholderLastNameInputText() {
        String placeholder = driver.findElement(By.xpath(LAST_NAME_INPUT)).getAttribute("placeholder");
        log.info("Last name placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in phone number")
    public String getPlaceholderPhoneNumberInputText() {
        String placeholder = driver.findElement(By.xpath(PHONE_NUMBER_INPUT)).getAttribute("placeholder");
        log.info("Phone number placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in email")
    public String getPlaceholderEmailInputText() {
        String placeholder = driver.findElement(By.xpath(EMAIL_INPUT)).getAttribute("placeholder");
        log.info("Email placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in password")
    public String getPlaceholderPasswordInputText() {
        String placeholder = driver.findElement(By.xpath(PASSWORD_INPUT)).getAttribute("placeholder");
        log.info("Password placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in confirm password")
    public String getPlaceholderConfirmPasswordInputText() {
        String placeholder = driver.findElement(By.xpath(CONFIRM_PASSWORD_INPUT)).getAttribute("placeholder");
        log.info("Confirm password placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Check that phone dropdown is displayed")
    public boolean isDropDownSelectorDisplayed() {
        boolean isVisible = driver.findElement(By.xpath(PHONE_DROPDOWN)).isDisplayed();
        log.info("Phone dropdown visibility: {}", isVisible);
        return isVisible;
    }

}