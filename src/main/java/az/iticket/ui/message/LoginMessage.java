package az.iticket.ui.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum LoginMessage {

    EMAIL_REQUIRED("email.required"),
    EMAIL_INVALID("email.invalid"),
    EMAIL_INVALID_FORMAT("email.invalid.format"),
    USER_NOT_FOUND("user.not.found"),
    INVALID_CREDENTIALS("invalid.credentials"),
    PASSWORD_MIN_LENGTH("password.min.length"),
    PASSWORD_MAX_LENGTH("password.max.length"),
    PASSWORD_REQUIRED("password.required"),
    EMAIL_MAX_LENGTH("email.max.length"),
    PASSWORD_TOO_SHORT("password.too.short");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = LoginMessage.class.getClassLoader()
                .getResourceAsStream("login.ui_ru.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл login.ui_ru.properties не найден в src/test/resources");
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

    LoginMessage(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}