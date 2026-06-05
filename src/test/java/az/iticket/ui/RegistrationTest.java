package az.iticket.ui;

import az.iticket.basetest.BaseTest;
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
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with empty first name input")
    @Test
    public void submitRegistrationFormWithEmptyFirstName() {
        registrationPage.fillRegistrationForm(
                "",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле имя обязательно для заполнения.", registrationPage.getEmptyEmailErrorMessage());
    }

    @DisplayName("Sumbit registration form with cirilic first name")
    @Test
    public void submitRegistrationFormWithCirilicFirstName() {
        registrationPage.fillRegistrationForm(
                TestDataGenerator.getFirstNameRu(),
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with hyphenated first name")
    @Test
    public void submitRegistrationFormWithHyphenatedFirstName() {
        registrationPage.fillRegistrationForm(
                "Anna-Maria",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit regisration form with one-character first name")
    @Test
    public void submitRegistrationFormWithOneCharacterFirstName() {
        registrationPage.fillRegistrationForm("K",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with 254 characters")
    @Test
    public void submitRegistrationFormWith254Characters() {
        String firstName = TestDataGenerator.getFirstNameByLength(254);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Accept first name with 255 characters")
    @Test
    public void submitRegistrationFormWith255Characters() {
        String firstName = TestDataGenerator.getFirstNameByLength(255);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with 256 characters")
    @Test
    public void submitRegistrationFormWith256Characters() {
        String firstName = TestDataGenerator.getFirstNameTooLong(256);
        registrationPage.fillRegistrationForm(firstName,
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Количество символов в поле имя не может превышать 255.", registrationPage.getLongFirstNameErrorMessage());
    }

    //Система принимает спецсиволы в поле FirstName,регистрация проходит пользователь входит в систему.
    @DisplayName("Accept first name with only symbols")
    @Test
    public void submitRegistrationFormWithSymbols() {
        registrationPage.fillRegistrationForm("!@#$%^&*()",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());

    }

    //Система принимает только числа в поле FirstName,регистрация проходит пользователь входит в систему.
    @DisplayName("Accept first name with only digits")
    @Test
    public void submitRegistrationFormWithDigits() {
        registrationPage.fillRegistrationForm("12345678910",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with leading space")
    @Test
    public void submitRegistrationFormWithLeadingSpace() {
        registrationPage.fillRegistrationForm(" Yekaterina",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    //Система сохраняет имя пользователя с пробелом по середине,возможно система поддерживает двойные имена.
    @DisplayName("Submit registration form with space in first name")
    @Test
    public void submitRegistrationFormWithSpaceInFirstName() {
        registrationPage.fillRegistrationForm("Anas tasiya",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registation form with first name ending spaces")
    @Test
    public void submitRegistrationFormWithFirstNameEndingSpaces() {
        registrationPage.fillRegistrationForm("Yekaterina ",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with first name only spaces ")
    @Test
    public void submitRegistrationFormWithOnlySpaces() {
        registrationPage.fillRegistrationForm("         ",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле имя обязательно для заполнения.", registrationPage.getEmptyEmailErrorMessage());
    }

    //Поле First name принимает значени табуляции и сохраняет ее как часть значения без нормализации.
    @DisplayName("Accept first name TAB characters")
    @Test
    public void submitRegistrationFormWithTabCharacters() {
        registrationPage.fillRegistrationForm("Jo\\thn",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    //Поле First name принимает значение новой строки и сохраняет ее как часть значения без нормализации.
    @DisplayName("Accept first name new line characters")
    @Test
    public void submitRegistrationFormWithNewLineCharacters() {
        registrationPage.fillRegistrationForm("Micha\\nel",
                TestDataGenerator.getLastName(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with empty last name")
    @Test
    public void submitRegistrationFormWithEmptyLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле фамилия обязательно для заполнения.", registrationPage.getEmptyLastNameErrorMessage());
    }

    @DisplayName("Submit registration form with cirilic last name")
    @Test
    public void submitRegistrationFormWithCirilicLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                TestDataGenerator.getLastNameRu(),
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.",registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with hyphenated last name")
    @Test
    public void submitRegistrationFormWithHyphenatedLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Martin-Clark",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit regisration form with one-character last name")
    @Test
    public void submitRegistrationFormWithOneCharacterLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "L",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with 254 characters")
    @Test
    public void submitRegistrationFormWithAllCharactersLastName() {
        String lastName = TestDataGenerator.getLastNameByLength(254);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with 255 characters")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameWith255() {
        String lastName = TestDataGenerator.getLastNameByLength(255);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with 255 characters")
    @Test
    public void submitRegistrationFormWithAllCharactersLastNameWith256() {
        String lastName = TestDataGenerator.getLastNameTooLong(256);
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                lastName,
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Количество символов в поле фамилия не может превышать 255.",registrationPage.getLongLastNameErrorMessage());
    }

    @DisplayName("Accept last name with only symbols")
    @Test
    public void submitRegistrationFormWithOnlySymbolsLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "!!!@@@@###$$$$%%%",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Accept last name with only digits")
    @Test
    public void submitRegistrationFormWithOnlyDigitsLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "1122334455667788",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name with leading space")
    @Test
    public void submitRegistrationFormWithLeadingSpaceLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                " Akberov",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with space in last name")
    @Test
    public void submitRegistrationFormWithSpacesInLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Iva nov",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with last name ending spaces ")
    @Test
    public void submitRegistrationFormWithSpacesInFirstName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Aliyev ",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with last name only spaces")
    @Test
    public void submitRegistrationFormWithOnlySpacesInFirstName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "          ",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле фамилия обязательно для заполнения.", registrationPage.getEmptyLastNameErrorMessage());
    }

    @DisplayName("Accept last name TAB characters")
    @Test
    public void submitRegistrationFormWithAllCharactersTabLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Silanty\\teva",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept last name new line characters")
    @Test
    public void submitRegistrationFormWithAllCharactersNewLineLastName() {
        registrationPage.fillRegistrationForm(TestDataGenerator.getFirstName(),
                "Isa\\nyeva",
                TestDataGenerator.getPhoneNumberAz(),
                TestDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }





}
