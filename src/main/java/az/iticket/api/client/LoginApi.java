package az.iticket.api.client;

import az.iticket.api.endpoints.Endpoints;
import az.iticket.api.model.LoginRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class LoginApi {
    public static Response login(LoginRequest request) {
        log.info("Login request: {}", request.toString());
        Response response =  given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(Endpoints.LOGIN);

        log.info("Login response status code: {}", response.getStatusCode());
        return response;
    }
}
