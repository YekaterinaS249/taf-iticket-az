package az.iticket.api.test;

import az.iticket.api.client.RegistrationApi;
import az.iticket.api.data.RegistrationDataFactory;
import az.iticket.api.message.RegistrationApiMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationApiTest {

    @DisplayName("Verify short phone number -API-REG-001")
    @Test
    public void registerPhoneNumber() {
        RegistrationApi.registration(RegistrationDataFactory.getShortPhoneNumber())
                .then()
                .statusCode(200)
                .body("response.email_verification.message", equalTo(RegistrationApiMessage.EMAIL_CONFIRM_MESSAGE.getMessage()));

    }

    @DisplayName("Verify max length number -API-REG-002")
    @Test
    public void registerMaxLengthNumber() {
        RegistrationApi.registration(RegistrationDataFactory.getMaxLengthPhoneNumber())
                .then()
                .statusCode(200)
                .body("response.email_verification.message", equalTo(RegistrationApiMessage.EMAIL_CONFIRM_MESSAGE.getMessage()));
    }

    @DisplayName("Verify phone number only letters -API-REG-003")
    @Test
    public void registerPhoneNumberOnlyLetters() {
        RegistrationApi.registration(RegistrationDataFactory.getPhoneNumberFieldRejectsLetters())
                .then()
                .statusCode(200)
                .body("response.email_verification.message", equalTo(RegistrationApiMessage.EMAIL_CONFIRM_MESSAGE.getMessage()));
    }

    @DisplayName("Verify phone number only symbols -API-REG-004")
    @Test
    public void registerPhoneNumberOnlySymbols() {
        RegistrationApi.registration(RegistrationDataFactory.getPhoneNumberFieldRejectsOnlySymbols())
                .then()
                .statusCode(200)
                .body("response.email_verification.message", equalTo(RegistrationApiMessage.EMAIL_CONFIRM_MESSAGE.getMessage()));
    }
}


