package az.iticket.api.test;

import az.iticket.api.client.RecoverPassApi;
import az.iticket.api.data.RecoverPassDataFactory;
import az.iticket.api.message.RecoverPassMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class RecoverPassApiTest {
    @DisplayName("Empty email input returns validation error -API-RCP-001")
    @Test
    public void recoverPasswordWithEmptyEmailReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getEmptyEmail())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.EMPTY_EMAIL_ERROR_MESSAGE));

    }

    @DisplayName("Valid registered email returns success message -API-RCP-002")
    @Test
    public void recoverPasswordWithValidRegisteredEmailReturnsSuccessTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getMessageRegisrtedUser())
                .then()
                .statusCode(200)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.RESET_LINK_SUCCESS_MESSAGE));
    }

    @DisplayName("Invalid email without @ symbol returns format error -API-RCP-003")
    @Test
    public void recoverPasswordWithMissingAtSymbolReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailMessageWithoutAtSymbols())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Invalid email with double @ symbol returns format error -API-RCP-004")
    @Test
    public void recoverPasswordWithDoubleAtSymbolReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailMessageWithDoubleAtSymbols())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Invalid email without domain returns format error -API-RCP-005")
    @Test
    public void recoverPasswordWithoutDomainReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailMessageWithoutDomainPart())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Invalid email without username returns format error -API-RCP-006")
    @Test
    public void recoverPasswordWithoutUsernameReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithoutUsername())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Invalid email with dot after @ returns format error -API-RCP-007")
    @Test
    public void recoverPasswordWithDotAfterAtSymbolReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithDotAfterAtSymbols())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Email with Cyrillic characters is rejected -API-RCP-008")
    @Test
    public void recoverPasswordWithCyrillicCharactersReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithCyrillicUsername())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Email without domain suffix is rejected -API-RCP-009")
    @Test
    public void recoverPasswordWithoutDomainSuffixReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithoutDomainSuffixIsRejected())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Invalid email with dot before @ returns format error -API-RCP-010")
    @Test
    public void recoverPasswordWithDotBeforeAtSymbolReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithDotBeforeAtSymbols())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Email with tab characters is rejected -API-RCP-012")
    @Test
    public void recoverPasswordWithTabCharactersReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailMessageWithTabCharacters())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));

    }

    @DisplayName("Email with newline characters is rejected -API-RCP-013")
    @Test
    public void recoverPasswordWithNewLineCharactersReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailMessageWithNewLineCharacters())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Email with leading space is accepted -API-RCP-014")
    @Test
    public void recoverPasswordWithLeadingSpaceReturnsSuccessTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithStartingSpaces())
                .then()
                .statusCode(200)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.RESET_LINK_SUCCESS_MESSAGE));

    }

    @DisplayName("Email with middle spaces is rejected -API-RCP-015")
    @Test
    public void recoverPasswordWithInternalSpacesReturnsValidationErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithContainsMiddlwSpaces())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE));
    }

    @DisplayName("Email with trailing space is accepted -API-RCP-016")
    @Test
    public void recoverPasswordWithTrailingSpaceReturnsSuccessTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithEndingSpaces())
                .then()
                .statusCode(200)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.RESET_LINK_SUCCESS_MESSAGE));
    }

    @DisplayName("Email with only spaces returns empty email error -API-RCP-017")
    @Test
    public void recoverPasswordWithOnlySpacesReturnsEmptyEmailErrorTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getInvalidEmailWithOnlySpaces())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.EMPTY_EMAIL_ERROR_MESSAGE));
    }

    @DisplayName("Unregistered email still returns success response -API-RCP-018")
    @Test
    public void recoverPasswordWithUnregisteredEmailReturnsSuccessTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getMessageNotRegisteredUser())
                .then()
                .statusCode(200)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.RESET_LINK_SUCCESS_MESSAGE));
    }

    @DisplayName("email exceeding max length returns validation errors -API-RCP-019")
    @Test
    public void recoverPasswordWithExceedingMaxLengthReturnsMultipleValidationErrorsTest() {
        RecoverPassApi.recoverPassword(RecoverPassDataFactory.getEmailMaxLength())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo(RecoverPassMessage.INVALID_FORMAT_EMAIL_MESSAGE))
                .body("response[0].messages[1]", equalTo(RecoverPassMessage.MAX_LENGTH_EMAIL_MESSAGE));

    }

}


