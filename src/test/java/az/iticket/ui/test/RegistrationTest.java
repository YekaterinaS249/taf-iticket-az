package az.iticket.ui.test;

import az.iticket.ui.basetest.BaseTest;
import az.iticket.ui.message.RegistrationMessage;
import az.iticket.ui.pages.AuthPage;
import az.iticket.ui.pages.RegistrationPage;
import az.iticket.ui.data.UserDataFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Epic("Authentication")
@Feature("Registration")
@Owner("Yekaterina Silantyeva")
public class RegistrationTest extends BaseTest {

    private AuthPage authPage;
    private RegistrationPage registrationPage;

    @BeforeEach
    public void initRegistrationPage() {
        homePage.clickAuthButton();
        authPage = new AuthPage();
        authPage.clickRegisterButton();
        registrationPage = new RegistrationPage();
    }

    @DisplayName("Registration succeeds with all valid user data -UI-REG-001")
    @Severity(SeverityLevel.CRITICAL)
    @Story("First Name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithValidData() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when first name is empty -UI-REG-002")
    @Severity(SeverityLevel.CRITICAL)
    @Story("First Name Field Validation")
    @Test
    public void shouldDisplayFirstNameRequiredErrorForEmptyFirstName() {
        registrationPage.fillRegistrationForm(
                "",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.FIRST_NAME_REQUIRED.getMessage(), registrationPage.getEmptyEmailErrorMessage(),
                "First name required error should be displayed");
    }

    @DisplayName("Registration succeeds with Cyrillic first name -UI-REG-003")
    @Severity(SeverityLevel.NORMAL)
    @Story("First Name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithCyrillicFirstName() {
        registrationPage.fillRegistrationForm(
                UserDataFactory.getFirstNameRu(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with hyphenated first name -UI-REG-004")
    @Severity(SeverityLevel.NORMAL)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithHyphenatedFirstName() {
        registrationPage.fillRegistrationForm(
                "Anna-Maria",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with one characters first name -UI-REG-005")
    @Severity(SeverityLevel.NORMAL)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithOneCharacterFirstName() {
        registrationPage.fillRegistrationForm("K",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with 254 characters first name -UI-REG-006 -UI-REG-006")
    @Severity(SeverityLevel.NORMAL)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWith254CharacterFirstName() {
        String firstName = UserDataFactory.getFirstNameByLength(254);
        registrationPage.fillRegistrationForm(firstName,
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Registration succeeds with 255 first name UI-REG-007")
    @Severity(SeverityLevel.NORMAL)
    @Story("First name Field Validation")
    @Test
    public void sshouldRegisterSuccessfullyWith255CharacterFirstName() {
        String firstName = UserDataFactory.getFirstNameByLength(255);
        registrationPage.fillRegistrationForm(firstName,
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when first name exceeds maximum length (256 characters) -UI-REG-008")
    @Severity(SeverityLevel.CRITICAL)
    @Story("First name Field Validation")
    @Test
    public void shouldDisplayFirstNameMaxLengthErrorFor256Characters() {
        String firstName = UserDataFactory.getFirstNameTooLong(256);
        registrationPage.fillRegistrationForm(firstName,
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.FIRST_NAME_MAX_LENGTH.getMessage(), registrationPage.getMaxLengthFistNameErrorMessage(),
                "First name max length error should be displayed");
    }

    @DisplayName("Registration succeeds with special characters as first name -UI-REG-009")
    @Severity(SeverityLevel.MINOR)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithSpecialCharactersFirstName() {
        registrationPage.fillRegistrationForm("!@#$%^&*()",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Registration succeeds with digits-only first name -UI-REG-010")
    @Severity(SeverityLevel.MINOR)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithDigitsOnlyFirstName() {
        registrationPage.fillRegistrationForm("12345678910",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with space in the middle of first name -UI-REG-011")
    @Severity(SeverityLevel.MINOR)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithSpaceInMiddleOfFirstName() {
        registrationPage.fillRegistrationForm("Anas tasiya",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with trailing space in first name -UI-REG-012")
    @Severity(SeverityLevel.MINOR)
    @Story("First name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithTrailingSpaceInFirstName() {
        registrationPage.fillRegistrationForm("Yekaterina ",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when first name contains only spaces -UI-REG-013")
    @Severity(SeverityLevel.CRITICAL)
    @Story("First name Field Validation")
    @Test
    public void shouldDisplayFirstNameRequiredErrorForSpacesOnlyFirstName() {
        registrationPage.fillRegistrationForm("      ",
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.FIRST_NAME_REQUIRED.getMessage(), registrationPage.getFirstNameRequiredErrorMessage(),
                "First name required error should be displayed");
    }

    @DisplayName("Validation error appears when last name is empty -UI-REG-014")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Last name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithHyphenatedLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.LAST_NAME_EMPTY.getMessage(), registrationPage.getEmptyLastNameErrorMessage(),
                "Last name required error should be displayed");
    }

    @DisplayName("Registration succeeds with Cyrillic last name -UI-REG-015")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Filed Validation")
    @Test
    public void shouldDisplayLastNameRequiredErrorForEmptyLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "Martin-Clark",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with single-character last name -UI-REG-016")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithOneCharacterLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "L",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with 254-character last name -UI-REG-017")
    @Severity(SeverityLevel.NORMAL)
    @Story("Last name Filed Validation")
    @Test
    public void shouldRegisterSuccessfullyWith254CharacterLastName() {
        String lastName = UserDataFactory.getLastNameByLength(254);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                lastName,
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with 255-character last name (max boundary) -UI-REG-018")
    @Severity(SeverityLevel.NORMAL)
    @Story("Last name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWith255CharacterLastName() {
        String lastName = UserDataFactory.getLastNameByLength(255);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                lastName,
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when last name exceeds maximum length (256 characters) -UI-REG-019")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Last name Filed Validation")
    @Test
    public void shouldDisplayLastNameMaxLengthErrorFor256Characters() {
        String lastName = UserDataFactory.getLastNameTooLong(256);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                lastName,
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.LAST_NAME_MAX_LENGTH.getMessage(), registrationPage.getMaxLengthLastNameErrorMessage(),
                "Last name maximum length error should be displayed");
    }

    @DisplayName("Registration succeeds with special characters as last name -UI-REG-020")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Filed Validation")
    @Test
    public void shouldRegisterSuccessfullyWithSpecialCharactersLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "!!!@@@@###$$$$%%%",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Registration succeeds with digits-only last name -UI-REG-021")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Field validation")
    @Test
    public void shouldRegisterSuccessfullyWithDigitsOnlyLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "1122334455667788",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with leading space in last name -UI-REG-022")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithLeadingSpaceInLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                " Akberov",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with space in the middle of last name -UI-REG-023")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithSpaceInMiddleOfLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "Iva nov",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with trailing space in last name -UI-REG-024")
    @Severity(SeverityLevel.MINOR)
    @Story("Last name Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithTrailingSpaceInLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "Aliyev ",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when last name contains only spaces -UI-REG-025")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Last name Field Validation")
    @Test
    public void shouldDisplayLastNameRequiredErrorForSpacesOnlyLastName() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                "          ",
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.LAST_NAME_REQUIRED.getMessage(), registrationPage.getLastNameRequiredErrorMessage(),
                "Last name required error should be displayed");
    }

    @DisplayName("Validation error appears when phone number is too short -UI-LOG-026")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Phone number Field Validation")
    @Test
    public void shouldDisplayInvalidPhoneErrorForTooShortPhoneNumber() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                "5",
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_PHONE.getMessage(), registrationPage.getInvalidCredentialPhoneNumberErrorMessage(),
                "Invalid phone number error should be displayed");
    }

    @DisplayName("Validation error appears for phone number with 6 digits (prefix +994 excluded) -UI-REG-027")
    @Story("Phone number Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldDisplayInvalidPhoneErrorForSixDigitPhoneNumber() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                "55",
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_PHONE.getMessage(), registrationPage.getInvalidCredentialPhoneNumberErrorMessage(),
                "Invalid phone number error should be displayed");
    }

    @DisplayName("Registration succeeds with minimum valid phone number (7 digits, prefix +994) -UI-REG-028")
    @Story("Phone number Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldRegisterSuccessfullyWithMinimumValidPhoneNumber() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                "555",
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Registration succeeds with 14-digit phone number (prefix +994) -UI-REG-029")
    @Story("Phone number Field Validation")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldRegisterSuccessfullyWith14DigitPhoneNumber() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                "5078390399",
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Validation error appears when phone number exceeds maximum length (prefix +994) -UI-REG-030")
    @Story("Phone number Field Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldDisplayInvalidPhoneErrorForTooLongPhoneNumber() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                "507839039393939999",
                UserDataFactory.getEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_PHONE.getMessage(), registrationPage.getInvalidCredentialPhoneNumberErrorMessage(),
                "Invalid phone number error should be displayed");
    }

    @DisplayName("Validation error appears when email field is empty -UI-REG-031")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayEmailRequiredErrorForEmptyEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                "",
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.EMAIL_REQUIRED.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Email required error should be displayed");
    }

    @DisplayName("Validation error appears when email is already registered -UI-REG-032")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayEmailAlreadyExistsErrorForRegisteredEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                "y22888836@gmail.com",
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.EMAIL_ALREADY_EXISTS.getMessage(), registrationPage.getErrorMessageEmailAlreadyExists(),
                "Email already exists error should be displayed");
    }

    @DisplayName("Registration succeeds with dot in email username -UI-REG-033")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithDotInEmailUsername() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithDot(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with plus in email username -UI-REG-034")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithPlusInEmailUsername() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getUserWithEmailContainingPlus(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with subdomain in email -UI-REG-035")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithSubdomainInEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithSubDomain(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when email is missing @ symbol -UI-REG-036")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithoutAtSymbol() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithoutAt(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Validation error appears when email is missing domain part -UI-REG-037")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithoutDomain() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithoutDomain(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Validation error appears when email is missing username part -UI-REG-038")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithoutUsername() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithoutUserName(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Validation error appears when email contains multiple @ symbols -UI-REG-039")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithDoubleAtSymbol() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                "gshyofyqrnelomfhla@@kjkpc.net",
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Registration succeeds with leading space in email -UI-REG-040")
    @Severity(SeverityLevel.MINOR)
    @Story("Email Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithLeadingSpaceInEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailContainsLeadingSpace(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when email contains space in the middle -UI-REG-041")
    @Severity(SeverityLevel.NORMAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithSpaceInMiddle() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailContainsSpaceInMiddle(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Registration succeeds with ending space in email -UI-REG-042")
    @Severity(SeverityLevel.MINOR)
    @Story("Email Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithTrailingSpaceInEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailContainsEndingSpace(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when email contains only spaces -UI-REG-043")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForSpacesOnlyEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                "     ",
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Validation error appears when email contains tab characters -UI-REG-044")
    @Severity(SeverityLevel.MINOR)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithTabCharacters() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithTabCharacters(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");

    }

    @DisplayName("Validation error appears when email contains new line character -UI-REG-045")
    @Severity(SeverityLevel.MINOR)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayInvalidEmailErrorForEmailWithNewLineCharacters() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmailWithNewLineCharacters(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.INVALID_EMAIL.getMessage(), registrationPage.getInvalidEmailCredentialsErrorMessage(),
                "Invalid email error should be displayed");
    }

    @DisplayName("Validation error appears when email exceeds maximum allowed length -UI-REG-046")
    @Severity(SeverityLevel.MINOR)
    @Story("Email Field Validation")
    @Test
    public void shouldDisplayEmailMaxLengthErrorForTooLongEmail() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getMaxLengthEmail(),
                UserDataFactory.getPassword());
        assertEquals(RegistrationMessage.MAX_EMAIL_LENGTH.getMessage(), registrationPage.getMaxLengthEmailErrorMessage(),
                "Maximum allowed length error should be displayed");
    }

    @DisplayName("Validation error appears when password field is empty -UI-REG-047")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Password Field Validation")
    @Test
    public void shouldDisplayPasswordMinLengthErrorForEmptyPassword() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                "");
        assertEquals(RegistrationMessage.MIN_PASSWORD_LENGTH.getMessage(), registrationPage.getEmptyPasswordErrorMessage(),
                "Password min length error should be displayed");
    }

    @DisplayName("Validation error appears when password is 1 character long -UI-REG-48")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Password field Validation")
    @Test
    public void shouldDisplayPasswordMinLengthErrorForOneCharacterPassword() {
        String shortPassword = UserDataFactory.getPasswordByLength(1);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                shortPassword);
        assertEquals(RegistrationMessage.MIN_PASSWORD_LENGTH.getMessage(), registrationPage.getShortPasswordErrorMessage(),
                "Password min length error should be displayed");
    }

    @DisplayName("Validation error appears when password is 7 characters -UI-REG-049")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Password Field Validation")
    @Test
    public void shouldDisplayPasswordMinLengthErrorForSevenCharacterPassword() {
        String invalidPassword = UserDataFactory.getPasswordByLength(7);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                invalidPassword);
        assertEquals(RegistrationMessage.MIN_PASSWORD_LENGTH.getMessage(), registrationPage.getShortPasswordErrorMessage(),
                "Password min length error should be displayed");

    }

    @DisplayName("Registration succeeds with minimum valid password length (8 characters) -UI-REG-050")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Password Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithMinimumValidPasswordLength() {
        String minLengthPassword = UserDataFactory.getPasswordByLength(8);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                minLengthPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with 9-character password -UI-REG-051")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithNineCharacterPassword() {
        String validPassword = UserDataFactory.getPasswordByLength(9);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                validPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with 254-character password -UI-REG-052")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWith254CharacterPassword() {
        String validPassword = UserDataFactory.getPasswordByLength(254);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                validPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage());

    }

    @DisplayName("Registration succeeds with 255-character password -UI-REG-053")
    @Severity(SeverityLevel.NORMAL)
    @Story("Password Field validation")
    @Test
    public void shouldRegisterSuccessfullyWith255CharacterPassword() {
        String validPassword = UserDataFactory.getPasswordByLength(255);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                validPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Validation error appears when password exceeds maximum length (256 characters) -UI-REG-054")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Password Field Validation")
    @Test
    public void shouldDisplayPasswordMaxLengthErrorFor256CharacterPassword() {
        String invalidPassword = UserDataFactory.getPasswordByLength(256);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                invalidPassword);
        assertEquals(RegistrationMessage.MAX_PASSWORD_LENGTH.getMessage(), registrationPage.getErrorMessageLongPassword(),
                "Password min length error should be displayed");

    }

    @DisplayName("Registration succeeds with special characters as password -UI-REG-055")
    @Severity(SeverityLevel.MINOR)
    @Story("Password Filed Validation")
    @Test
    public void  shouldRegisterSuccessfullyWithSpecialCharactersPassword() {
        String symbolsPassword = UserDataFactory.getSpecialSymbolsPassword(8);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                symbolsPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage());

    }

    @DisplayName("Registration succeeds with digits-only password -UI-REG-056")
    @Severity(SeverityLevel.MINOR)
    @Story("Password Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithDigitsOnlyPassword() {
        String digitsPassword = UserDataFactory.getOnlyDigitsPassword(8);
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                digitsPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Registration succeeds with leading spaces in password -UI-REG-057")
    @Severity(SeverityLevel.MINOR)
    @Story("Password Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithLeadingSpaceInPassword() {
        String leadingSpacesPassword = UserDataFactory.getPasswordWithLeadingPassword();
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                leadingSpacesPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");
    }

    @DisplayName("Registration succeeds with spaces in the middle of password -UI-REG-058")
    @Severity(SeverityLevel.MINOR)
    @Story("Password Field VValidation")
    @Test
    public void shouldRegisterSuccessfullyWithSpaceInMiddleOfPassword() {
        String middleSpacesPassword = UserDataFactory.getPasswordWithMiddlePassword();
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                middleSpacesPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Registration succeeds with trailing spaces in password -UI-REG-059")
    @Severity(SeverityLevel.MINOR)
    @Story("Password Field Validation")
    @Test
    public void shouldRegisterSuccessfullyWithTrailingSpaceInPassword() {
        String endingSpacesPassword = UserDataFactory.getPasswordWithEndingPassword();
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                endingSpacesPassword);
        assertEquals(RegistrationMessage.SUCCESS_REGISTER.getMessage(), registrationPage.getSuccessMessage(),
                "Success message should be displayed");

    }

    @DisplayName("Validation error appears when password contains only spaces -UI-REG-060")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Password Field Password")
    @Test
    public void shouldDisplayPasswordRequiredErrorForSpacesOnlyPassword() {
        registrationPage.fillRegistrationForm(UserDataFactory.getFirstName(),
                UserDataFactory.getLastName(),
                UserDataFactory.getPhoneNumberAz(),
                UserDataFactory.getEmail(),
                "          ");
        assertEquals(RegistrationMessage.PASSWORD_REQUIRED.getMessage(), registrationPage.getErrorMessageEmptyPassword(),
                "Password required error should be displayed");
    }

    @DisplayName("Registration page displays correct title text -UI-REG-061")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("UI Elements")
    @Test
    public void shouldDisplayRegistrationPageTitle() {
        registrationPage.getRegistrationTitle();
        assertEquals("Регистрация", registrationPage.getRegistrationTitle(),
                "Registration page title should be displayed");
    }

    @DisplayName("Registration page displays correct footer text -UI-REG-062")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("UI Elements")
    @Test
    public void shouldDisplayRegistrationFooterTitle() {
        registrationPage.getRegistrationFooterTitle();
        assertEquals("Уже зарегистрирован?", registrationPage.getRegistrationFooterTitle(),
                "Registration footer title should be displayed");
    }

    @DisplayName("Clicking login link opens login modal from registration page -UI-REG-063")
    @Severity(SeverityLevel.NORMAL)
    @Story("UI Elements")
    @Test
    public void shouldOpenLoginModalFromRegistrationPage() {
        registrationPage.clickLoginButton();
        Assertions.assertTrue(registrationPage.isModalLoginDisplayed(),
                "Login modal should be displayed");
    }

    @DisplayName("First name input displays correct placeholder text -UI-REG-064")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("UI Elements")
    @Test
    public void shouldDisplayFirstNamePlaceholderText() {
        assertEquals("Введите имя", registrationPage.getPlaceholderFirstNameInputText(),
                "First name placeholder should be displayed");
    }

    @DisplayName("Last name input displays correct placeholder text -UI-REG-065")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("UI Elements")
    @Test
    public void shouldDisplayLastNamePlaceholderText() {
        assertEquals("Введите фамилию", registrationPage.getPlaceholderLastNameInputText(),
                "Last name placeholder should be displayed");
    }


    @DisplayName("Country code dropdown selector is visible on registration form -UI-REG-066")
    @Severity(SeverityLevel.NORMAL)
    @Story("UI Elements")
    @Test
    public void shouldDisplayEmailPlaceholderText() {
        assertEquals("name@example.com", registrationPage.getPlaceholderEmailInputText(),
                "Email placeholder should be displayed");
    }

    @DisplayName("Clicking close button dismisses the registration modal -UI-REG-067")
    @Severity(SeverityLevel.NORMAL)
    @Story("UI Elements")
    @Test
    public void shouldDisplayCountryCodeDropdownSelector() {
        Assertions.assertTrue(registrationPage.isDropDownSelectorDisplayed(),
                "Country code dropdown should be displayed");
    }

    @DisplayName("Clicking close button dismisses the registration modal -UI-REG-067")
    @Severity(SeverityLevel.NORMAL)
    @Story("UI ELements")
    @Test
    public void shouldCloseRegistrationModalOnCloseButtonClick() {
        registrationPage.clickCloseModalButton();
        Assertions.assertTrue(registrationPage.isModalRegistrationInvisible(),
                "Registration modal should be visible");

    }
}




