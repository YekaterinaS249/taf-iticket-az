package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationPage extends BasePage {
    final String FIRST_NAME_INPUT = "//input[@id='first_name']";
    final String LAST_NAME_INPUT = "//input[@name='last_name']";
    final String PHONE_NUMBER_INPUT = "//input[@id='mobile']";
    final String EMAIL_INPUT = "//input[@id='email']";
    final String PASSWORD_INPUT = "//input[@id='password']";
    final String REGISTRATION_BUTTON = "//span[text()='Регистрация']";
    final String REGISTRATION_TITLE = "//div[text()='Регистрация']";
    final String REGISTRATION_FOOTER_TITTLE = "//p[contains(text(),'Уже есть аккаунт?')]";
    final String LOGIN_BUTTON = "//button[contains(@class,'btn-link-primary') and contains(.,'Войти')]";
    final String CLOSE_MODAL_BUTTON = "//button[@aria-label='Close modal']";
    final String SUCCESS_REGISTER_MESSAGE = "//div[contains(@class,'break-words') and contains(.,'Подтвердите свой e-mail')]";
    final String EMPTY_FIRST_NAME_ERROR_MESSAGE = "//div[@id='first_name-hint']";
    final String MAX_LENGTH_FIRST_NAME_MESSAGE  = "//p[text()='Количество символов в поле имя не может превышать 255.']";
    final String FIRST_NAME_REQUIRED_MESSAGE = "//p[text()='Поле имя обязательно для заполнения.']";
    final String EMPTY_LAST_NAME_ERROR_MESSAGE = "//div[@id='last_name-hint']";
    final String MAX_LENGTH_LAST_NAME_MESSAGE = "//p[text()='Количество символов в поле фамилия не может превышать 255.']";
    final String LAST_NAME_REQUIRED_MESSAGE = "//p[text()='Поле фамилия обязательно для заполнения.']";
    final String INVALID_PHONE_NUMBER_MESSAGE = "//div[@id='mobile-hint']";
    final String EMAIL_ALREADY_EXITS_ERROR_MESSAGE = "//p[text()='Такое значение поля e-mail адрес уже существует.']";
    final String INVALID_EMAIL_CDERENTIALS_ERROR_MESSAGE = "//div[@id='email-hint']";
    final String MAX_LENGTH_EMAIl_MESSAGE = "//p[text()='Количество символов в поле e-mail адрес не может превышать 255.']";
    final String EMPTY_PASSWORD_ERROR_MESSAGE = "//p[text()='Поле пароль обязательно для заполнения.']";
    final String INVALID_PASSWORD_ERROR_MESSAGE = "//div[@id='password-hint']";
    final String MAX_LENGTH_PASSWORD_MESSAGE = "//p[text()='Количество символов в поле пароль не может превышать 255.']";
    final String LOGIN_MODAL = "//div[contains(@class,'rounded-xl') and contains(@class,'bg-light-surface')]";
    final String CHOOSE_COUNTRY_BUTTON = "//button[@aria-label='Choose country']";
    final String REGISTRATION_MODAL = "//div[text()='Регистрация']/ancestor::div[contains(@class,'rounded-xl')]";


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
    public void fillRegistrationForm(String firstName, String lastName, String phoneNumber, String email, String password) {
        log.info("Filling registration form with data: firstName = {} ,lastName = {},phoneNumber= {},email= {},password = {}",
                firstName, lastName, phoneNumber, email, password);
        type(FIRST_NAME_INPUT, firstName);
        type(LAST_NAME_INPUT, lastName);
        type(PHONE_NUMBER_INPUT, phoneNumber);
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(REGISTRATION_BUTTON);
        log.info("Clicking on the registration button");
    }

    public void type(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public void click(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    @Step("User click on the login button")
    public void clickLoginButton() {
        log.info("Clicking on the login button");
        WebElement loginButton = driver.findElement(By.xpath(LOGIN_BUTTON));
        loginButton.click();
    }

    @Step("User click on the close button")
    public void clickCloseModalButton() {
        log.info("Clicking on the close button");
        driver.findElement(By.xpath(CLOSE_MODAL_BUTTON)).click();
    }

    @Step("Get success registration message")
    public String getSuccessMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUCCESS_REGISTER_MESSAGE))).getText();
        log.info("Succes message: {}", text);
        return text;
    }

    @Step("Get empty email error message")
    public String getEmptyEmailErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_FIRST_NAME_ERROR_MESSAGE))).getText();
        log.info("Empty email message: {}", text);
        return text;
    }

    @Step("Get max length first name error message")
    public String getMaxLengthFistNameErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_FIRST_NAME_MESSAGE))).getText();
        log.info("Max length first name message: {}", text);
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
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_LAST_NAME_MESSAGE))).getText();
        log.info("Long email error message: {}", text);
        return text;
    }

    @Step("Get invalid credentials phone number error message")
    public String getInvalidCredentialPhoneNumberErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_PHONE_NUMBER_MESSAGE))).getText();
        log.info("Invalid credentials error message: {}", text);
        return text;
    }

    @Step("Get first name required error message")
    public String getFirstNameRequiredErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_NAME_REQUIRED_MESSAGE))).getText();
        log.info("First name required error message: {}", text);
        return text;
    }

    @Step("Get last name required error message")
    public String getLastNameRequiredErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LAST_NAME_REQUIRED_MESSAGE))).getText();
        log.info("Last name required error message: {}", text);
        return text;
    }

    @Step("Get max length email error message")
    public String getMaxLengthEmailErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_EMAIl_MESSAGE))).getText();
        log.info("Max length email error message: {}", text);
        return text;
    }

    @Step("Get user already exits error message")
    public String getErrorMessageEmailAlreadyExists() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_ALREADY_EXITS_ERROR_MESSAGE))).getText();
        log.info("Email already exists error message: {}", text);
        return text;
    }

    @Step("Get invalid email credentials error message")
    public String getInvalidEmailCredentialsErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_EMAIL_CDERENTIALS_ERROR_MESSAGE))).getText();
        log.info("Invalid email credentials error message: {}", text);
        return text;
    }

    @Step("Get empty password error message")
    public String getEmptyPasswordErrorMessage() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_PASSWORD_ERROR_MESSAGE))).getText();
        log.info("Empty password error message: {}", text);
        return text;
    }

    @Step("Get short password error message")
    public String getShortPasswordErrorMessage() {
       String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_PASSWORD_ERROR_MESSAGE))).getText();
       log.info("Short password error message: {}", text);
        return text;
    }

    @Step("Get max length password error message")
    public String getErrorMessageLongPassword() {
        String text= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_LENGTH_PASSWORD_MESSAGE))).getText();
        log.info("Long email error message: {}", text);
        return text;
    }

    @Step("Get empty password error message")
    public String getErrorMessageEmptyPassword() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_PASSWORD_ERROR_MESSAGE))).getText();
        log.info("Empty password error message: {}", text);
        return text;
    }

    @Step("Login modal is displayed: {result}")
    public boolean isModalLoginDisplayed() {
        log.info("Checking  modal login is displayed");
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL)));
        return modal.isDisplayed();
    }

    @Step("Get placeholder text in first name")
    public String getPlaceholderFirstNameInputText() {
        String placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_NAME_INPUT))).getAttribute("placeholder");
        log.info("First name placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in last name")
    public String getPlaceholderLastNameInputText() {
        String placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LAST_NAME_INPUT))).getAttribute("placeholder");
        log.info("Last name placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Get placeholder text in email")
    public String getPlaceholderEmailInputText() {
        String placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMAIL_INPUT))).getAttribute("placeholder");
        log.info("Email placeholder: {}", placeholder);
        return placeholder;
    }

    @Step("Check that phone dropdown is displayed")
    public boolean isDropDownSelectorDisplayed() {
        boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CHOOSE_COUNTRY_BUTTON))).isDisplayed();
        log.info("Phone dropdown visibility: {}", isVisible);
        return isVisible;
    }
    @Step("Registration modal is invisible")
    public boolean isModalRegistrationInvisible() {
        boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(REGISTRATION_MODAL)));
        log.info("Registration modal visibility: {}", invisible);
        return invisible;
    }
}





