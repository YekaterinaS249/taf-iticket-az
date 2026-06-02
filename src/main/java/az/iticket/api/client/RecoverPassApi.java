package az.iticket.api.client;

import az.iticket.api.endpoints.Endpoints;
import az.iticket.api.model.RecoverPassRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class RecoverPassApi {
    public static Response recoverPass(RecoverPassRequest passRequest) {
        return given()
                .contentType(ContentType.JSON)
                .body(passRequest)
                .when()
                .post(Endpoints.RECOVER_PASSWORD);

    }
}
