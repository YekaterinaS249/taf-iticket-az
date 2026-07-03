package az.iticket.ui.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum RegistrationMessage {

    SUCCESS_REGISTER("success.message.register"),
    FIRST_NAME_EMPTY("first.name.empty.input"),
    FIRST_NAME_MAX_LENGTH("first.name.max.length"),
    FIRST_NAME_REQUIRED("first.name.required"),
    LAST_NAME_EMPTY("last.name.empty.input"),
    LAST_NAME_MAX_LENGTH("last.name.max.length"),
    LAST_NAME_REQUIRED("last.name.required"),
    INVALID_PHONE("phone.invalid"),
    EMAIL_REQUIRED("email.required"),
    EMAIL_ALREADY_EXISTS("email.already.exists"),
    MAX_EMAIL_LENGTH("email.max.length"),
    INVALID_EMAIL("email.invalid"),
    PASSWORD_REQUIRED("password.required"),
    MIN_PASSWORD_LENGTH("password.min.length"),
    MAX_PASSWORD_LENGTH("password.max.length");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = RegistrationMessage.class.getClassLoader()
                .getResourceAsStream("registration.ui_ru.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Properties file not found: registration.ui_ru.properties");
            }

            try (InputStreamReader reader =
                         new InputStreamReader(input, StandardCharsets.UTF_8)) {
                PROPERTIES.load(reader);
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load registration.ui_ru.properties", e);
        }
    }

    private final String key;

    RegistrationMessage(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}