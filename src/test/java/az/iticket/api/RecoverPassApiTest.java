package az.iticket.api;

import az.iticket.api.client.RecoverPassApi;
import az.iticket.api.model.RecoverPassRequest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class RecoverPassApiTest {
    @Test
    public void emptyEmailInputTest(){
       RecoverPassRequest passRequest = new RecoverPassRequest("");
       RecoverPassApi.recoverPass(passRequest)
               .then()
               .statusCode(422)
               .body("response[0].messages[0]",equalTo("Поле e-mail адрес обязательно для заполнения."));

    }
}
