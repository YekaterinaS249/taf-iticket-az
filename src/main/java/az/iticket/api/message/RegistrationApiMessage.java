package az.iticket.api.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum RegistrationApiMessage {

    EMAIL_CONFIRM_MESSAGE("email.confirm.message");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = RegistrationApiMessage.class.getClassLoader()
                .getResourceAsStream("registration.api_ru.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл registration.api_ru.properties не найден в src/test/resources");
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

    RegistrationApiMessage(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}