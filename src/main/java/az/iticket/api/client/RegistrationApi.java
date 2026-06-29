package az.iticket.api.client;

import az.iticket.api.endpoints.Endpoints;
import az.iticket.api.model.RegistrationRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RegistrationApi {
    public static Response registration(RegistrationRequest  registrationRequest) {
        log.info("Registration Request: {}", registrationRequest.toString());
        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(registrationRequest)
                .when()
                .post(Endpoints.REGISTER);

        log.info("Registration Response: {}", response.getStatusCode());
        return response;
    }
}

