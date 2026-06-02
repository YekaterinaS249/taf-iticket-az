package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RegistrationPage;
import az.iticket.utils.NegativeDataGenerator;
import az.iticket.utils.PositiveDataGenerator;
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
        password = PositiveDataGenerator.getPassword();

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
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле имя обязательно для заполнения.", registrationPage.getEmptyEmailErrorMessage());
    }

    @DisplayName("Sumbit registration form with cirilic first name")
    @Test
    public void submitRegistrationFormWithCirilicFirstName() {
        registrationPage.fillRegistrationForm(
                PositiveDataGenerator.getFirstNameRu(),
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with hyphenated first name")
    @Test
    public void submitRegistrationFormWithHyphenatedFirstName() {
        registrationPage.fillRegistrationForm(
                "Anna-Maria",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit regisration form with one-character first name")
    @Test
    public void submitRegistrationFormWithOneCharacterFirstName() {
        registrationPage.fillRegistrationForm("K",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with 254 characters")
    @Test
    public void submitRegistrationFormWith254Characters() {
        String firstName = PositiveDataGenerator.getFirstNameByLength(254);
        registrationPage.fillRegistrationForm(firstName,
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());

    }

    @DisplayName("Accept first name with 255 characters")
    @Test
    public void submitRegistrationFormWith255Characters() {
        String firstName = PositiveDataGenerator.getFirstNameByLength(255);
        registrationPage.fillRegistrationForm(firstName,
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with 256 characters")
    @Test
    public void submitRegistrationFormWith256Characters() {
        String firstName = NegativeDataGenerator.getFirstNameTooLong(256);
        registrationPage.fillRegistrationForm(firstName,
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Количество символов в поле имя не может превышать 255.", registrationPage.getLongCredentialsErrorMessage());
    }

    //Система принимает спецсиволы в поле FirstName,регистрация проходит пользователь входит в систему,валидация отсутсвует.
    @DisplayName("Accept first name with only symbols")
    @Test
    public void submitRegistrationFormWithSymbols() {
        registrationPage.fillRegistrationForm("!@#$%^&*()",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());

    }

    //Система принимает только числа в поле FirstName,регистрация проходит пользователь входит в систему,валидация отсутвует.
    @DisplayName("Accept first name with only digits")
    @Test
    public void submitRegistrationFormWithDigits() {
        registrationPage.fillRegistrationForm("12345678910",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Accept first name with leading space")
    @Test
    public void submitRegistrationFormWithLeadingSpace() {
        registrationPage.fillRegistrationForm(" Yekaterina",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    //Система сохраняет имя пользователя с пробелом по середине,возможно система поддерживает двойные имена.
    @DisplayName("Submit registration form with space in first name")
    @Test
    public void submitRegistrationFormWithSpaceInFirstName() {
        registrationPage.fillRegistrationForm("Anas tasiya",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registation form with first name ending spaces")
    @Test
    public void submitRegistrationFormWithFirstNameEndingSpaces() {
        registrationPage.fillRegistrationForm("Yekaterina ",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Пожалуйста, подтвердите e-mail, чтобы продолжить пользоваться сайтом.", registrationPage.getConfrimEmailMessage());
    }

    @DisplayName("Submit registration form with first name only spaces ")
    @Test
    public void submitRegistrationFormWithOnlySpaces() {
        registrationPage.fillRegistrationForm("         ",
                PositiveDataGenerator.getLastName(),
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле имя обязательно для заполнения.", registrationPage.getEmptyEmailErrorMessage());
    }

    @DisplayName("Submit registration form with empty last name")
    @Test
    public void submitRegistrationFormWithEmptyLastName() {
        registrationPage.fillRegistrationForm(PositiveDataGenerator.getFirstName(),
                "",
                PositiveDataGenerator.getPhoneNumberAz(),
                PositiveDataGenerator.getEmail(),
                password,
                password);
        Assertions.assertEquals("Поле фамилия обязательно для заполнения.",registrationPage.getEmptyLastNameErrorMessage());
    }
}
