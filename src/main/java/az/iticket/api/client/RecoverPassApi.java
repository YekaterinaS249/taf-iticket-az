package az.iticket.api.client;

import az.iticket.api.endpoints.Endpoints;
import az.iticket.api.model.RecoverPassRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RecoverPassApi {
    public static Response recoverPassword(RecoverPassRequest passRequest) {
        log.info("RecoverPass Request: {}", passRequest.toString());
        Response response= given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(passRequest)
                .when()
                .post(Endpoints.RECOVER_PASSWORD);

        log.info("RecoverPass Response status code: {}", response.getStatusCode());
        return response;
    }
}


