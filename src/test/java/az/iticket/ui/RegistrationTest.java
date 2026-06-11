package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import az.iticket.constans.RegistrationMessage;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RegistrationPage;
import az.iticket.utils.TestDataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("Registration user with valid data")
    @Test
    public void registrationUserWithValidData() {
        registrationPage.fillRegistrationForm(
                "Yekaterina",
                "Kalin",
                "507839039",
                "y22888836@gmail.com",
                "test1234",
                "test1234");
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with empty first name input")
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

    @DisplayName("Sumbit registration form with cirilic first name")
    @Test
    public void submitRegistrationFormWithCirilicFirstNameTest() {
        registrationPage.fillRegistrationForm(
                TestDataGenerator.getFirstNameRu(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with hyphenated first name")
    @Test
    public void submitRegistrationFormWithHyphenatedFirstNameTest() {
        registrationPage.fillRegistrationForm(
                "Anna-Maria",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit regisration form with one-character first name")
    @Test
    public void submitRegistrationFormWithOneCharacterFirstNameTest() {
        registrationPage.fillRegistrationForm("K",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with 254 characters")
    @Test
    public void submitRegistrationFormWith254CharactersTest() {
        String firstName = TestDataGenerator.getFirstNameByLength(254);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Accept first name with 255 characters")
    @Test
    public void submitRegistrationFormWith255CharactersTest() {
        String firstName = TestDataGenerator.getFirstNameByLength(255);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with 256 characters")
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
    @DisplayName("Accept first name with only symbols")
    @Test
    public void submitRegistrationFormWithSymbolsTest() {
        registrationPage.fillRegistrationForm("!@#$%^&*()",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    //Система принимает только числа в поле FirstName,регистрация проходит пользователь входит в систему.
    @DisplayName("Accept first name with only digits")
    @Test
    public void submitRegistrationFormWithDigitsTest() {
        registrationPage.fillRegistrationForm("12345678910",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with leading space")
    @Test
    public void submitRegistrationFormWithLeadingSpaceTest() {
        registrationPage.fillRegistrationForm(" Yekaterina",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    //Система сохраняет имя пользователя с пробелом по середине,возможно система поддерживает двойные имена.
    @DisplayName("Submit registration form with space in first name")
    @Test
    public void submitRegistrationFormWithSpaceInFirstNameTest() {
        registrationPage.fillRegistrationForm("Anas tasiya",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registation form with first name ending spaces")
    @Test
    public void submitRegistrationFormWithFirstNameEndingSpacesTest() {
        registrationPage.fillRegistrationForm("Yekaterina ",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with first name only spaces ")
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
    @DisplayName("Accept first name TAB characters")
    @Test
    public void submitRegistrationFormWithTabCharactersTest() {
        registrationPage.fillRegistrationForm("Jo\\thn",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    //Поле First name принимает значение новой строки и сохраняет ее как часть значения без нормализации.
    @DisplayName("Accept first name new line characters")
    @Test
    public void submitRegistrationFormWithNewLineCharactersTest() {
        registrationPage.fillRegistrationForm("Micha\\nel",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with empty last name")
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

    @DisplayName("Submit registration form with cirilic last name")
    @Test
    public void submitRegistrationFormWithCirilicLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastNameRu(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMPTY_LAST_NAME_INPUT_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with hyphenated last name")
    @Test
    public void submitRegistrationFormWithHyphenatedLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Martin-Clark",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with one-character last name")
    @Test
    public void submitRegistrationFormWithOneCharacterLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "L",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with 254 characters")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameTest() {
        String lastName = TestDataGenerator.getLastNameByLength(254);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with 255 characters")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameWith255Test() {
        String lastName = TestDataGenerator.getLastNameByLength(255);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with 256 characters")
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

    @DisplayName("Accept last name with only symbols")
    @Test
    public void submitRegistrationFormWithOnlySymbolsLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "!!!@@@@###$$$$%%%",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Accept last name with only digits")
    @Test
    public void submitRegistrationFormWithOnlyDigitsLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "1122334455667788",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with leading space")
    @Test
    public void submitRegistrationFormWithLeadingSpaceLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                " Akberov",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with space in last name")
    @Test
    public void submitRegistrationFormWithSpacesInLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Iva nov",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with last name ending spaces ")
    @Test
    public void submitRegistrationFormWithSpacesInFirstNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Aliyev ",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with last name only spaces")
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

    @DisplayName("Accept last name TAB characters")
    @Test
    public void submitRegistrationFormWithAllCharactersTabLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Silanty\\teva",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name new line characters")
    @Test
    public void submitRegistrationFormWithAllCharactersNewLineLastNameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Isa\\nyeva",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with empty phone input")
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

    @DisplayName("Submit registration form with short phone number")
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
    @DisplayName("Verify validation for Azerbaijan phone number with 11 digits")
    @Test
    public void verifyValidationForAzerbaijanPhoneNumberWith11DigitsTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                "55555555",
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    /* Поле номера для Азербайджана принимает 13 цифр
    хотя максимально допустимая длина допускает 12 цифр включая префикс
     */
    @DisplayName("Verify validation for Azerbaijan phone number with 13 digits")
    @Test
    public void submitRegistrationFormWithInvalidLenghtPhoneNumberTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getInvalidLengthNumber(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    /* Система валидирует только максимально превышающею длину строки
    для номера телефона
     */
    @DisplayName("Veriry validation for Azerbaijan too long phone number")
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

    @DisplayName("Verify validation for phone input with only spaces")
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

    @DisplayName("Verify validation with empty email input")
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

    @DisplayName("Verify validation registration with register email")
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

    @DisplayName("Verify email validation with dot in username")
    @Test
    public void verifyEmailValidationWithDotInUsernameTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithDot(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify email validation with containing plus")
    @Test
    public void verifyEmailValidationWithContainsPlusTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getUserWithEmailContainingPlus(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation email with sub domain")
    @Test
    public void verifyEmailValidationWithSubDomainTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailWithSubDomain(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation email without at symbols")
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

    @DisplayName("Verify validation email without domain")
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

    @DisplayName("Verify validation email without user name")
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

    @DisplayName("Verify validation email with double at symbols")
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

    @DisplayName("Verify validation email contains starting spaces")
    @Test
    public void verifyEmailValidationWithContainsStartingSpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailContainsLeadingSpace(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation email contains middle spaces")
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

    @DisplayName("Verify validation email contains ending spaces")
    @Test
    public void verifyEmailValidationWithEndingSpacesTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmailContainsEndingSpace(),
                password,
                password);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation email with only spaces")
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

    @DisplayName("Verify validation email with TAB characters")
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

    @DisplayName("Verify validation email with new line character")
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

    @DisplayName("Verify validation empty password input")
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

    @DisplayName("Verify validation with short password")
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

    @DisplayName("Verify validation invalid password length 7 symbols")
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

    @DisplayName("Verify validation password  with min length 8 symbols")
    @Test
    public void verifyEmailValidationMinLength8SymbolsTest() {
        String minLengthPassword = TestDataGenerator.getPasswordByLength(8);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                minLengthPassword,
                minLengthPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation password 9 symbols")
    @Test
    public void verifyEmailValidationPassword9SymbolsTest() {
        String validPassword = TestDataGenerator.getPasswordByLength(9);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                validPassword,
                validPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation password with 254 characters")
    @Test
    public void verifyEmailValidationPasswordWith254CharactersTest() {
        String validPassword = TestDataGenerator.getPasswordByLength(254);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                validPassword,
                validPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Verify validation password with 255 characters")
    @Test
    public void verifyEmailValidationPasswordWith255CharactersTest() {
        String validPassword = TestDataGenerator.getPasswordByLength(255);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                validPassword,
                validPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation password with 256 characters")
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
    @DisplayName("Verify validation password with only symbols")
    @Test
    public void verifyEmailValidationPasswordWithOnlySymbolsTest() {
        String symbolsPassword = TestDataGenerator.getSpecialSymbolsPassword(8);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                symbolsPassword,
                symbolsPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Verify validation password with only digits")
    @Test
    public void verifyEmailValidationPasswordWithOnlyDigitsTest() {
        String digitsPassword = TestDataGenerator.getOnlyDigitsPassword(8);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                digitsPassword,
                digitsPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    // Система не тримит пробелы в пароле.Пароль чувствилен к whitespaces.
    @DisplayName("Verify validation password with leading spaces")
    @Test
    public void verifyEmailValidationPasswordWithLeadingSpacesTest() {
        String leadingSpacesPassword = TestDataGenerator.getPasswordWithLeadingPassword();
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                leadingSpacesPassword,
                leadingSpacesPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Verify validation password contains middle spaces")
    @Test
    public void verifyEmailValidationPasswordWithMiddleSpacesTest() {
        String middleSpacesPassword = TestDataGenerator.getPasswordWithMiddlePassword();
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                middleSpacesPassword,
                middleSpacesPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Verify validation password with ending spaces")
    @Test
    public void verifyEmailValidationPasswordWithEndingSpacesTest() {
        String endingSpacesPassword = TestDataGenerator.getPasswordWithEndingPassword();
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                endingSpacesPassword,
                endingSpacesPassword);
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Verify validation password only spaces ")
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
    @DisplayName("Verify validation empty confirm password")
    @Test
    public void verifyEmptyConfirmPasswordTest() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                TestDataGenerator.getPassword(),
                "");
        Assertions.assertEquals(RegistrationMessage.EMAIL_VERIFICATION_REQUIRED_MESSAGE, registrationPage.getConfrimEmailMessage());
    }

}

