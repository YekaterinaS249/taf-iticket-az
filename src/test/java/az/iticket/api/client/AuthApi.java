package az.iticket.api.client;

import az.iticket.api.Endpoints;
import az.iticket.model.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi {
    public static Response login(LoginRequest request) {
        return  given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(Endpoints.LOGIN);
    }
}