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

    @Test
    public void validEmailMessageTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("katyatest97@gmail.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(200)
                .body("response[0].messages[0]",equalTo("Ссылка на сброс пароля была отправлена!"));
    }

    @Test
    public void invalidEmailMessageTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("testtest.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void invalidEmailMessageWithDoubleAtSymbolTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("test@@test.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void invalidEmailMessageWithoutDomenTest() {
        RecoverPassRequest passRequest = new RecoverPassRequest("test@");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void invalidEmailMessageWithTabCharactersTest() {
        RecoverPassRequest passRequest = new RecoverPassRequest("test\\t@test.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));

    }

    @Test
    public void invalidEmailMessageWithNewLineCharactersTest() {
        RecoverPassRequest passRequest = new RecoverPassRequest("test\\n@test.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void invalidEmailMessageWithStartingSpaceTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest(" test@gmail.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(200)
                .body("response[0].messages[0]", equalTo("Ссылка на сброс пароля была отправлена!"));

    }

    @Test
    public void invalidEmailMessageWithMiddleSpaceTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("test @test.com");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void invalidEmailMessageWithEndingSpaceTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("test@test.com ");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(200)
                .body("response[0].messages[0]",equalTo("Ссылка на сброс пароля была отправлена!"));
     }

     @Test
    public void invalidEmailMessageWithOnlySpacesTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("              ");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес обязательно для заполнения."));
     }

     @Test
    public void notRegisterUserTest(){
        RecoverPassRequest passRequest = new RecoverPassRequest("hatlfaxkhovnnrzaqr@onldm.net");
        RecoverPassApi.recoverPass(passRequest)
                .then()
                .statusCode(200)
                .body("response[0].messages[0]" , equalTo("Ссылка на сброс пароля была отправлена!"));
     }

     @Test
    public void longEmailMessageTest(){
         String email = "a".repeat(64) + "@" + "b".repeat(187) + ".com";
         RecoverPassRequest passRequest = new RecoverPassRequest(email);
         RecoverPassApi.recoverPass(passRequest)
                 .then()
                 .statusCode(422)
                 .body("response[0].messages[0]",equalTo("Поле e-mail адрес должно быть действительным электронным адресом."))
                 .body("response[0].messages[1]", equalTo("Количество символов в поле e-mail адрес не может превышать 255."));

     }

}

