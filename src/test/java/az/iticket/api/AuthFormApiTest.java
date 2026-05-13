package az.iticket.api;

import az.iticket.api.client.AuthApi;
import az.iticket.api.request.LoginRequest;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class AuthFormApiTest {

    @Test
    public void testLoginWithValidCredentials() {
        LoginRequest loginRequest = new LoginRequest("twbstuoqtylygmnqau@gonrr.net","test4321");
        AuthApi.login(loginRequest)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void WithoutPasswordTest() {

        Faker faker = new Faker();
        String email = faker.name().username() + "@test.com";
        String password = "";
        LoginRequest loginRequest = new LoginRequest(email,password);
        AuthApi.login(loginRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле пароль обязательно для заполнения."));
    }

    @Test
    public void WithoutEmailTest() {
        Faker faker = new Faker();
        String email = "";
        String password = faker.internet().password();
        LoginRequest loginRequest = new LoginRequest(email,password);
        AuthApi.login(loginRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес обязательно для заполнения."));

    }

    @Test
    public void wrongPasswordTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkz@vtmpj.com","12345678");

        AuthApi.login(loginRequest)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));
    }

    @Test
    public void wrongEmailTest() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "test1234";
        LoginRequest loginRequest = new LoginRequest(email,password);
        AuthApi.login(loginRequest)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));
    }

    @Test
    public void loginWithoutEmailAndPasswordTest() {
        String email = "";
        String password = "";
        LoginRequest loginRequest = new LoginRequest(email,password);
        AuthApi.login(loginRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес обязательно для заполнения."))
                .body("response[1].messages[0]", equalTo("Поле пароль обязательно для заполнения."));

    }

    @Test
    public void loginWithEmailWithoutAtSymbolTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkzvtmpj.com","katya199");
                AuthApi.login(loginRequest)
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void loginWithEmailWithoutDomenTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkz@","katya199");
                AuthApi.login(loginRequest)
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void loginWithLongEmailTest() {
        LoginRequest loginRequest = new LoginRequest("silantyevayekaterinaaaaaaaaaaaaa@gmail.com","maryam17");
                AuthApi.login(loginRequest)
                        .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));

    }

    @Test
    public void loginWithShortPasswordTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkz@vtmpj.com","t");
                AuthApi.login(loginRequest)
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Количество символов в поле пароль должно быть не меньше 8."));

    }

    @Test
    public void loginWithInvalidLenghtTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkz@vtmpj.com","test123");
                AuthApi.login(loginRequest)
                        .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Количество символов в поле пароль должно быть не меньше 8."));

    }

    @Test
    public void loginWithLongPasswordTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkz@vtmpj.com","test12345");
           AuthApi.login(loginRequest)
                   .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));
    }

    @Test
    public void loginWithPasswordOnlySpacesTest() {
        LoginRequest loginRequest = new LoginRequest("cqqgslqadspnhazzkz@vtmpj.com","           ");
        AuthApi.login(loginRequest)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле пароль обязательно для заполнения."));

    }

    @Test
    public void loginWithEmailOnlySpacesTest() {
       LoginRequest loginRequest = new LoginRequest("                    ","test1234");
       AuthApi.login(loginRequest)
               .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес обязательно для заполнения."));
    }

    @Test
    public void LoginNotRegisterUserTest(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8,8);
        LoginRequest loginRequest = new LoginRequest(email,password);
        AuthApi.login(loginRequest)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));

    }

    @Test
    public void errorWithoutEmailInputTest(){
        Faker faker = new Faker();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(faker.internet().password(8,8));
        AuthApi.login(loginRequest)
                .then()
                .statusCode(422);
    }

    @Test
    public void errorWithoutPasswordInputTest(){
        Faker faker = new Faker();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(faker.internet().emailAddress());
        AuthApi.login(loginRequest)
                .then()
                .statusCode(422);
    }
}

