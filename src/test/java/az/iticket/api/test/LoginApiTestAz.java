package az.iticket.api.test;

import az.iticket.api.client.LoginApiAz;
import az.iticket.api.data.AuthDataFactory;
import az.iticket.api.message.LoginApiMessageAz;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.core.IsEqual.equalTo;


@Epic("Authentication")
@Feature("Login API")
@Owner("Silantyeva Yekaterina")
public class LoginApiTestAz {

    @DisplayName("Verify login without password -API-AZ-LOG-001")
    @Test
    public void loginWithEmptyPasswordTest() {
        LoginApiAz.login(AuthDataFactory.loginWithoutPassword())
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo(LoginApiMessageAz.PASSWORD_REQUIRED.getMessage()));

    }

    @DisplayName("Verify login with invalid credentials -API-AZ-LOG-002")
    @Test
    public void loginWithInvalidCredentialsTest() {
        LoginApiAz.login(AuthDataFactory.withInvalidCredentials())
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo(LoginApiMessageAz.INVALID_CREDENTIALS.getMessage()));
    }
}
