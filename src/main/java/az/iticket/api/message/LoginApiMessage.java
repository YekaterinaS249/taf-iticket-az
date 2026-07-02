package az.iticket.api.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum LoginApiMessage {

    PASSWORD_REQUIRED("password.required"),
    EMAIL_REQUIRED("email.required"),
    INVALID_CREDENTIALS("auth.invalid.credentials"),
    INVALID_EMAIL_FORMAT("email.invalid.format"),
    MAX_EMAIL_LENGTH("email.max.length"),
    MIN_PASSWORD_LENGTH("password.min.length"),
    MAX_PASSWORD_LENGTH("password.max.length");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = LoginApiMessage.class.getClassLoader()
                .getResourceAsStream("login.api_ru.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл login.api_ru.properties не найден в src/test/resources");
            }

            try (InputStreamReader reader =
                         new InputStreamReader(input, StandardCharsets.UTF_8)) {
                PROPERTIES.load(reader);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String key;

    LoginApiMessage(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}