package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import az.iticket.ui.message.RegistrationMessage;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RegistrationPage;
import az.iticket.utils.TestDataGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Epic("Authentication")
@Feature("Registration")
@Owner("Yekaterina Silantyeva")
public class RegistrationTest extends BaseTest {

    private AuthPage authPage;
    private RegistrationPage registrationPage;
    private String password;

    @BeforeEach
    public void initRegistrationPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage();
        authPage.clickRegisterButton();
        registrationPage = new RegistrationPage();
        password = TestDataGenerator.getPassword();

    }

    @DisplayName("REG-001 - Verify successful user registration with valid data")
    @Story("Successful user registration")
    @Test
    public void registrationUserWithValidData() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-002 -Verify validation error is displayed for empty first name")
    @Story("Register with empty first name")
    @Test
    public void submitRegistrationFormWithEmptyFirstNameTest() {
        registrationPage.fillRegistrationForm(
                "",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_FIRST_NAME_INPUT_MESSAGE, registrationPage.getEmptyEmailErrorMessage());
    }

    @DisplayName("REG-003 -Verify successful registration with Cyrillic first name")
    @Story("Register with Cyrillic first name")
    @Test
    public void submitRegistrationFormWithCyrillicFirstNameTest() {
        registrationPage.fillRegistrationForm(
                TestDataGenerator.getFirstNameRu(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-004 -Verify successful registration with hyphenated first name")
    @Story("Register with hyphenated first name")
    @Test
    public void submitRegistrationFormWithHyphenatedFirstNameTest() {
        registrationPage.fillRegistrationForm(
                "Anna-Maria",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-005 -Verify successful registration with one-character first name")
    @Story("Registration with one-character first name")
    @Test
    public void submitRegistrationFormWithOneCharacterFirstNameTest() {
        registrationPage.fillRegistrationForm("K",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-006 -Verify registration accepts a 254-character first name")
    @Story("Register with 254-character first name")
    @Test
    public void submitRegistrationFormWith254CharactersTest() {
        String firstName = TestDataGenerator.getFirstNameByLength(254);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-007 -Verify registration accepts a 255-character first name")
    @Story("Register with 255-character first name")
    @Test
    public void submitRegistrationFormWith255CharactersTest() {
        String firstName = TestDataGenerator.getFirstNameByLength(255);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-008 -Verify validation error for a 256-character first name")
    @Story("Register with 256-character first name")
    @Test
    public void submitRegistrationFormWith256CharactersTest() {
        String firstName = TestDataGenerator.getFirstNameTooLong(256);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.LONG_FIRST_NAME_MESSAGE, registrationPage.getLongFirstNameErrorMessage());
    }

    //Система принимает спецсиволы в поле FirstName,регистрация проходит пользователь входит в систему.
    @DisplayName("REG-009 -Verify registration accepts a first name containing only symbols")
    @Story("Register with symbols-only first name")
    @Test
    public void submitRegistrationFormWithSymbolsTest() {
        registrationPage.fillRegistrationForm("!@#$%^&*()",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    //Система принимает только числа в поле FirstName,регистрация проходит пользователь входит в систему.
    @DisplayName("REG-010 -Verify registration accepts a first name containing only digits")
    @Story("Register with digits-only first name")
    @Test
    public void submitRegistrationFormWithDigitsTest() {
        registrationPage.fillRegistrationForm("12345678910",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-011 -Verify leading space first name during registration")
    @Story("Register with leading space in first name")
    @Test
    public void submitRegistrationFormWithLeadingSpaceTest() {
        registrationPage.fillRegistrationForm(" Yekaterina",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    //Система сохраняет имя пользователя с пробелом по середине,возможно система поддерживает двойные имена.
    @DisplayName("REG-012 -Verify registration accepts first name with middle space")
    @Story("Register with middle space in first name")
    @Test
    public void submitRegistrationFormWithSpaceInFirstNameTest() {
        registrationPage.fillRegistrationForm("Anas tasiya",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-013 -Verify registration with trailing spaces in first name")
    @Story("Register with first name ending with spaces")
    @Test
    public void submitRegistrationFormWithFirstNameEndingSpacesTest() {
        registrationPage.fillRegistrationForm("Yekaterina ",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-014 -Verify validation error for first name containing only spaces")
    @Story("Register with whitespace-only first name")
    @Test
    public void submitRegistrationFormWithOnlySpacesTest() {
        registrationPage.fillRegistrationForm("         ",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_FIRST_NAME_INPUT_MESSAGE, registrationPage.getEmptyEmailErrorMessage());
    }

    //Поле First name принимает значени табуляции и сохраняет ее как часть значения без нормализации.
    @DisplayName(" REG-015 -Verify registration with tab characters in first name")
    @Story("Register with tab characters in first name")
    @Test
    public void submitRegistrationFormWithTabCharactersTest() {
        registrationPage.fillRegistrationForm("Jo\\thn",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    //Поле First name принимает значение новой строки и сохраняет ее как часть значения без нормализации.
    @DisplayName("REG-016 -Verify registration with new line characters in first name")
    @Story("Register with new line characters")
    @Test
    public void submitRegistrationFormWithNewLineCharactersTest() {
        registrationPage.fillRegistrationForm("Micha\\nel",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-017 -Verify validation error for empty last name")
    @Story("Register with empty last name")
    @Test
    public void submitRegistrationFormWithEmptyLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_LAST_NAME_INPUT_MESSAGE, registrationPage.getEmptyLastNameErrorMessage());
    }

    @DisplayName("REG-018 -Verify registration accepts Cyrillic last name")
    @Story("Registration with Cyrillic last name")
    @Test
    public void submitRegistrationFormWithCyrilicLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastNameRu(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-019 -Verify registration accepts hyphenated last name")
    @Story("Register with hyphenated last name")
    @Test
    public void submitRegistrationFormWithHyphenatedLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Martin-Clark",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-020 -Verify registration accepts one-character last name")
    @Story("Register with one-character last name")
    @Test
    public void submitRegistrationFormWithOneCharacterLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "L",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-021 -Verify registration accepts 254-character last name")
    @Story("Register with 254-character last name")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameTest() {
        String lastName = TestDataGenerator.getLastNameByLength(254);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-022 -Verify registration accepts 255-characters last name")
    @Story("Register with 255-characters last name")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameWith255Test() {
        String lastName = TestDataGenerator.getLastNameByLength(255);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-023 - Verify validation error for a 256-character last name")
    @Story("Register with 256-character last name")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameWith256Test() {
        String lastName = TestDataGenerator.getLastNameTooLong(256);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.LONG_LAST_NAME_MESSAGE, registrationPage.getLongLastNameErrorMessage());
    }

    @DisplayName("REG-024 -Verify registration accepts a last name containing only symbols")
    @Story("Register with symbols-only last name")
    @Test
    public void submitRegistrationFormWithOnlySymbolsLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "!!!@@@@###$$$$%%%",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-025 -Verify registration accepts last name containing only digits")
    @Story("Register with digits-only last name")
    @Test
    public void submitRegistrationFormWithOnlyDigitsLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "1122334455667788",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-026 -Verify successful registration with leading space in last name")
    @Story("Register with leading space in last name")
    @Test
    public void submitRegistrationFormWithLeadingSpaceLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                " Akberov",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-027 -Verify registration accepts middle space in last name")
    @Story("Register with middle space in last name")
    @Test
    public void submitRegistrationFormWithSpacesInLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Iva nov",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-028 -Verify successful registration with trailing spaces in last name")
    @Story("Register with ending spaces in last name")
    @Test
    public void submitRegistrationFormWithSpacesInFirstNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Aliyev ",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-029 -Verify validation error for last name containing only spaces")
    @Story("Register with whitespace-only last name")
    @Test
    public void submitRegistrationFormWithOnlySpacesInFirstNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "          ",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_LAST_NAME_INPUT_MESSAGE, registrationPage.getEmptyLastNameErrorMessage());
    }

    @DisplayName("REG-030 -Verify registration accepts tab characters in last name")
    @Story("Register with tab characters in last name")
    @Test
    public void submitRegistrationFormWithAllCharactersTabLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Silanty\\teva",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-031 -Verify registration accepts newline characters in last name")
    @Story("Register with newline characters in last name")
    @Test
    public void submitRegistrationFormWithAllCharactersNewLineLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Isa\\nyeva",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-032 -Verify validation error for empty phone number")
    @Story("Register with empty phone number")
    @Test
    public void submitRegistrationFormWithEmptyPhoneInputTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                "",
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_PHONE_NUMBER_ERROR_MESSAGE, registrationPage.getEmptyPhoneNumberErrorMessage());
    }

    @DisplayName("REG-032 -Verify validation error for short phone number")
    @Story("Register with short phone number")
    @Test
    public void submitRegistrationFormWithShortPhoneNumberTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                "5",
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_PHONE_NUMBER_ERROR_MESSAGE, registrationPage.getEmptyPhoneNumberErrorMessage());
    }

    /* Поле номера для Азербайждана принимает 11 цифр
    хотя корректная длина номера 12 цифр валидация отсутвует.
     */
    @DisplayName("REG-033 -Verify validation for Azerbaijan phone number with 11 digits")
    @Story("Register with Azerbaijan phone number (11 digits)")
    @Test
    public void verifyValidationForAzerbaijanPhoneNumberWith11DigitsTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                "55555555",
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    /* Поле номера для Азербайджана принимает 13 цифр
    хотя максимально допустимая длина допускает 12 цифр включая префикс
     */
    @DisplayName("REG-034 -Verify validation for Azerbaijan phone number with 13 digits")
    @Story("Register with Azerbaijan number(12 digits)")
    @Test
    public void submitRegistrationFormWithInvalidLenghtPhoneNumberTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getInvalidLengthNumber(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    /* Система валидирует только максимально превышающею длину строки
    для номера телефона
     */
    @DisplayName("REG-035 -Verify validation for Azerbaijan too long phone number")
    @Story("Register with too long Azerbaijan phone number")
    @Test
    public void verifyValidationForAzerbaijanTooLongPhoneNumberTest() {
        String phoneNumber = TestDataGenerator.getLongPhoneNumber(256);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                phoneNumber,
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.LONG_PHONE_NUMBER_ERROR_MESSAGE, registrationPage.getErrorMessageLongPhoneNumber());
    }

    @DisplayName("REG-036 -Verify validation for phone input with only spaces")
    @Story("Register with whitespace-only phone number")
    @Test
    public void verifyValidationForPhoneInputWithOnlySpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                "          ",
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_PHONE_NUMBER_ERROR_MESSAGE, registrationPage.getEmptyPhoneNumberErrorMessage());
    }

    @DisplayName("REG-037 -Verify validation with empty email input")
    @Story("Register with empty email")
    @Test
    public void verifyValidationWithEmptyEmailInputTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                "",
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_EMAIL_ERROR_MESSAGE, registrationPage.getErrorMessageEmptyEmail());
    }

    @DisplayName("REG-038 -Verify validation registration with already register email")
    @Story("Register with already registered email")
    @Test
    public void verifyValidationRegistrationWithRegisterEmailTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                "y22888836@gmail.com",
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_ALREADY_EXITS_ERROR_MESSAGE, registrationPage.getErrorMessageEmailAlreadyExists());
    }

    @DisplayName("REG-039 -Verify email validation with dot in username")
    @Story("Register with email containing dot in username")
    @Test
    public void verifyEmailValidationWithDotInUsernameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithDot(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-040 -Verify email validation with containing plus")
    @Story("Register with email containing plus sign")
    @Test
    public void verifyEmailValidationWithContainsPlusTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getUserWithEmailContainingPlus(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-041 -Verify validation email with sub domain")
    @Story("Register with email containing subdomain")
    @Test
    public void verifyEmailValidationWithSubDomainTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithSubDomain(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-042 -Verify validation email without at symbols")
    @Story("Email validation without @ symbol")
    @Test
    public void verifyEmailValidationWithoutAtSymbolsTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithoutAt(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());
    }

    @DisplayName("REG-043 -Verify validation email without domain")
    @Story("Register with email missing domain")
    @Test
    public void verifyEmailValidationWithoutDomainTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithoutDomen(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());
    }

    @DisplayName("REG-044 -Verify validation email without user name")
    @Story("Email validation without username")
    @Test
    public void verifyEmailValidationWithoutUserNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithoutUserName(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());
    }

    @DisplayName("REG-045 -Verify validation email with double at symbols")
    @Story("Email validation with multiple @ symbols")
    @Test
    public void verifyEmailValidationWithDoubleAtSymbolsTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                "gshyofyqrnelomfhla@@kjkpc.net",
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());
    }

    @DisplayName("REG-046 -Verify validation email contains starting spaces")
    @Story("Register with email starting with spaces")
    @Test
    public void verifyEmailValidationWithContainsStartingSpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailContainsLeadingSpace(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-047 -Verify validation email contains middle spaces")
    @Story("Register with email containing middle spaces")
    @Test
    public void verifyEmailValidationWithContainsMiddleSpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailContainsSpaceinMiddle(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());
    }

    @DisplayName("REG-048 -Verify validation email contains ending spaces")
    @Story("Register with email ending with spaces")
    @Test
    public void verifyEmailValidationWithEndingSpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailContainsEndingSpace(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-049 -Verify validation email with only spaces")
    @Story("Register with whitespace-only email")
    @Test
    public void verifyEmailValidationWithOnlySpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                "           ",
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_EMAIL_ERROR_MESSAGE, registrationPage.getEmptyEmailErrorMessage());
    }

    @DisplayName("REG-050 -Verify validation email with tab characters")
    @Story("Register with tab characters in email")
    @Test
    public void verifyEmailValidationWithTabCharactersTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithTabCharacters(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());

    }

    @DisplayName("REG-051 -Verify validation email with new line character")
    @Story("Register with newline character in email")
    @Test
    public void verifyEmailValidationWithNewLineCharactersTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithNewLineCharacters(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.INVALID_EMAIL_CREDENTIALS_MESSAGE, registrationPage.getInvalidEmailCredentialsErrorMessage());
    }

    @DisplayName("REG-052 -Verify validation empty password input")
    @Story("Register with empty password")
    @Test
    public void verifyEmailValidationEmptyPasswordTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                "",
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_PASSWORD_ERROR_MESSAGE, registrationPage.getEmptyPasswordErrorMessage());
    }

    @DisplayName("REG-053 -Verify validation with short password")
    @Story("Register with short password")
    @Test
    public void verifyEmailValidationShortPasswordTest() {
        String shortPassword = TestDataGenerator.getPasswordByLength(1);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                shortPassword,
                shortPassword);
        Assertions.assertEquals(RegistrationMessage.SHORT_PASSWORD_ERROR_MESSAGE, registrationPage.getShortPasswordErrorMessage());
    }

    @DisplayName("REG-054 -Verify validation invalid password length 7 symbols")
    @Story("Password validation for 7-character password")
    @Test
    public void verifyValidationInvalidPasswordLength() {
        String invalidPassword = TestDataGenerator.getPasswordByLength(7);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                invalidPassword,
                invalidPassword);
        Assertions.assertEquals(RegistrationMessage.SHORT_PASSWORD_ERROR_MESSAGE, registrationPage.getShortPasswordErrorMessage());

    }

    @DisplayName("REG-055 -Verify validation password  with min length 8 symbols")
    @Story("Register with minimum length password (8 characters)")
    @Test
    public void verifyEmailValidationMinLength8SymbolsTest() {
        String minLengthPassword = TestDataGenerator.getPasswordByLength(8);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                minLengthPassword,
                minLengthPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-056 -Verify validation password 9 symbols")
    @Story("Register with 9-character password")
    @Test
    public void verifyEmailValidationPassword9SymbolsTest() {
        String validPassword = TestDataGenerator.getPasswordByLength(9);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                validPassword,
                validPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-057 -Verify validation password with 254 characters")
    @Story("Register with 254-character password")
    @Test
    public void verifyEmailValidationPasswordWith254CharactersTest() {
        String validPassword = TestDataGenerator.getPasswordByLength(254);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                validPassword,
                validPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-058 -Verify validation password with 255 characters")
    @Story("Register with 255-character password")
    @Test
    public void verifyEmailValidationPasswordWith255CharactersTest() {
        String validPassword = TestDataGenerator.getPasswordByLength(255);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                validPassword,
                validPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-059 -Verify validation error password with 256 characters")
    @Story("Register with 256-character password")
    @Test
    public void verifyEmailValidationPasswordWith256CharactersTest() {
        String invalidPassword = TestDataGenerator.getPasswordByLength(256);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                invalidPassword,
                invalidPassword);
        Assertions.assertEquals(RegistrationMessage.LONG_PASSWORD_ERROR_MESSAGE, registrationPage.getErrorMessageLongPassword());

    }

    // Поле пароль принимает любые символы,валидируется только длина.
    @DisplayName("REG-060 -Verify validation password with only symbols")
    @Story("Register with symbols-only password")
    @Test
    public void verifyEmailValidationPasswordWithOnlySymbolsTest() {
        String symbolsPassword = TestDataGenerator.getSpecialSymbolsPassword(8);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                symbolsPassword,
                symbolsPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-061 -Verify validation password with only digits")
    @Story("Register with digits-only password")
    @Test
    public void verifyEmailValidationPasswordWithOnlyDigitsTest() {
        String digitsPassword = TestDataGenerator.getOnlyDigitsPassword(8);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                digitsPassword,
                digitsPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    // Система не тримит пробелы в пароле.Пароль чувствилен к whitespaces.
    @DisplayName("REG-062 -Verify validation password with leading spaces")
    @Story("Register with leading spaces in password")
    @Test
    public void verifyEmailValidationPasswordWithLeadingSpacesTest() {
        String leadingSpacesPassword = TestDataGenerator.getPasswordWithLeadingPassword();
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                leadingSpacesPassword,
                leadingSpacesPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    @DisplayName("REG-063 -Verify validation password contains middle spaces")
    @Story("Register with middle spaces in password")
    @Test
    public void verifyEmailValidationPasswordWithMiddleSpacesTest() {
        String middleSpacesPassword = TestDataGenerator.getPasswordWithMiddlePassword();
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                middleSpacesPassword,
                middleSpacesPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-064 -Verify validation password with ending spaces")
    @Story("Registration with password ending with spaces")
    @Test
    public void verifyEmailValidationPasswordWithEndingSpacesTest() {
        String endingSpacesPassword = TestDataGenerator.getPasswordWithEndingPassword();
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                endingSpacesPassword,
                endingSpacesPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-065 -Verify validation password only spaces ")
    @Story("Register with whitespace-only password")
    @Test
    public void verifyEmailValidationPasswordOnlySpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                "          ",
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_PASSWORD_ERROR_MESSAGE, registrationPage.getEmptyPasswordErrorMessage());
    }

    //Система позволяет завершить регистрацию при пустом поле подтверждения пароля. Валидация обязательности поля отсутствует.
    @DisplayName("REG-066 -Verify validation empty confirm password")
    @Story("Register with empty confirm password")
    @Test
    public void verifyEmptyConfirmPasswordTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                TestDataGenerator.getPassword(),
                "");
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());
    }

    //Система позволяет зарегистрироваться при несовпадении значений Password и Confirm Password.
    @DisplayName("REG-067 -Verify validation password and confirm password mismatch")
    @Story("Register with password mismatch")
    @Test
    public void verifyPasswordAndConfirmPasswordMismatchTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                "user1234");
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfirmEmailMessage());

    }

    @DisplayName("REG-068 -Verify registration page title")
    @Story("Registration page title text presence")
    @Test
    public void verifyRegistrationPageTitleTest() {
        registrationPage.getTitle();
        Assertions.assertEquals("Регистрация", registrationPage.getTitle());
    }

    @DisplayName("REG-069 -Verify registration footer title")
    @Story("Registration page footer text presence")
    @Test
    public void verifyRegistrationFooterTitleTest() {
        registrationPage.getFooterTitle();
        Assertions.assertEquals("Уже зарегистрирован?", registrationPage.getFooterTitle());
    }

    @DisplayName("REG-070 -Verify navigation to login page from registration page after click login link")
    @Story("Navigation from registration page to login page")
    @Test
    public void verifyNavigationToLoginPageFromRegistrationPageTest() {
        registrationPage.clickLoginButton();
        Assertions.assertTrue(registrationPage.isModalLoginDisplayed());
    }

    @DisplayName("REG-071 -Get placeholder text in first name input")
    @Story("Verify placeholder text in first name input")
    @Test
    public void getPlaceholderFirstNameInputTest() {
        Assertions.assertEquals("Имя", registrationPage.getPlaceholderFirstNameInputText());
    }

    @DisplayName("REG-072 -Get placeholder text in last name input")
    @Story("Verify placeholder text in last name input")
    @Test
    public void getPlaceholderLastNameInputTest() {
        Assertions.assertEquals("Фамилия", registrationPage.getPlaceholderLastNameInputText());
    }

    @DisplayName("REG-073 -Get placeholder text in phone number input")
    @Story("Verify placeholder text in phone input")
    @Test
    public void getPlaceholderPhoneNumberInputTest() {
        Assertions.assertEquals("Мобильный", registrationPage.getPlaceholderPhoneNumberInputText());
    }

    @DisplayName("REG-074 -Get placeholder text in email input")
    @Story("Verify placeholder text in email input")
    @Test
    public void getPlaceholderEmailInputTest() {
        Assertions.assertEquals("E-mail", registrationPage.getPlaceholderEmailInputText());
    }

    @DisplayName("REG-075 -Get placeholder text in password input")
    @Story("Verify placeholder text in password input")
    @Test
    public void getPlaceholderPasswordInputTest() {
        Assertions.assertEquals("Пароль", registrationPage.getPlaceholderPasswordInputText());
    }

    @DisplayName("REG-076 -Get placeholder text in confir password input")
    @Story("Verify placeholder text in confirm password")
    @Test
    public void getPlaceholderConfirmPasswordInputTest() {
        Assertions.assertEquals("Подтвердить пароль", registrationPage.getPlaceholderConfirmPasswordInputText());
    }

    @DisplayName("REG-077 -Verify dropdown selector is displayed")
    @Story("Dropdown selector visibility")
    @Test
    public void dropDownSelectorDisplayedTest() {
        Assertions.assertTrue(registrationPage.isDropDownSelectorDisplayed());
    }


}

