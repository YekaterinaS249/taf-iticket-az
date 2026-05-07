package az.iticket.api;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class AuthFormApiTest {

    @Test
    public void testLoginWithValidCredentials() {
        String email = "cqqgslqadspnhazzkz@vtmpj.com";
        String password = "test1234";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void WithoutPasswordTest() {

        Faker faker = new Faker();
        String email = faker.name().username() + "@test.com";
        String password = "";
        System.out.println("Email: " + email);

        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле пароль обязательно для заполнения."));
    }

    @Test
    public void WithoutEmailTest() {
        Faker faker = new Faker();
        String email = "";
        String password = faker.internet().password();
        ;
        System.out.println("Password: " + password);

        String body = """
                {
                "email": "%s",
                "password": "%s"
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес обязательно для заполнения."));

    }

    @Test
    public void wrongPasswordTest() {
        String email = "cqqgslqadspnhazzkz@vtmpj.com";
        String password = "12345678";

        String body = """
                {
                "email": "%s" ,
                "password": "%s"
                }
                """.formatted(email, password);

        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));
    }

    @Test
    public void wrongEmailTest() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "test1234";
        System.out.println("Email: " + email);

        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));
    }

    @Test
    public void loginWithoutEmailAndPasswordTest() {
        String email = "";
        String password = "";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес обязательно для заполнения."))
                .body("response[1].messages[0]", equalTo("Поле пароль обязательно для заполнения."));


    }

    @Test
    public void loginWithEmailWithoutAtSymbolTest() {
        String email = "cqqgslqadspnhazzkzvtmpj.com";
        String password = "katya199";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));


    }

    @Test
    public void loginWithEmailWithoutDomenTest() {
        String email = "cqqgslqadspnhazzkz@";
        String password = "katya199";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле e-mail адрес должно быть действительным электронным адресом."));
    }

    @Test
    public void loginWithLongEmailTest() {
        String URL = "https://api.iticket.az/ru/v5/user/auth/token";
        String email = "silantyevayekaterinaaaaaaaaaaaaa@gmail.com";
        String password = "maryam17";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(URL)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));

    }

    @Test
    public void loginWithShortPasswordTest() {
        String email = "cqqgslqadspnhazzkz@vtmpj.com";
        String password = "t";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Количество символов в поле пароль должно быть не меньше 8."));

    }

    @Test
    public void loginWithInvalidLenghtTest() {
        String email = "cqqgslqadspnhazzkz@vtmpj.com";
        String password = "test123";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Количество символов в поле пароль должно быть не меньше 8."));

    }

    @Test
    public void loginWithLongPasswordTest() {
        String email = "cqqgslqadspnhazzkz@vtmpj.com";
        String password = "test12345";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));
    }

    @Test
    public void loginWithPasswordOnlySpacesTest() {
        String email = "cqqgslqadspnhazzkz@vtmpj.com";
        String password = "        ";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]", equalTo("Поле пароль обязательно для заполнения."));

    }

    @Test
    public void loginWithEmailOnlySpacesTest() {
        String email = "                               ";
        String password = "test1234";
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
        .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422)
                .body("response[0].messages[0]",equalTo("Поле e-mail адрес обязательно для заполнения."));
    }

    @Test
    public void LoginNotRegisterUserTest(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8,8);
        System.out.println(email +" " + password);
        String body = """
                {
                "email": "%s",
                "password": "%s"
                }
                """.formatted(email, password);
        given()
        .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(403)
                .body("response[0].messages[0]", equalTo("The provided credentials do not match our records."));

    }

    @Test
    public void errorWithoutEmailInputTest(){
        Faker faker = new Faker();
        String password = faker.internet().password(8,8);
        String body = """
                {
                "password": "%s"
                }
                """.formatted(password);
        given()
        .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422);
    }

    @Test
    public void errorWithoutPasswordInputTest(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String body = """
                {
                "email": "%s",
                }
                """;
        given()
        .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(422);
    }
}

