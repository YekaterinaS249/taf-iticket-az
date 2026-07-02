package az.iticket.api.message;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum RecoverPassApiMessage {

    EMAIL_REQUIRED("email.required"),
    RESET_LINK_SUCCESS("reset.link.success"),
    EMAIL_INVALID_FORMAT("email.invalid.format"),
    EMAIL_MAX_LENGTH("email.max.length");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = RecoverPassApiMessage.class.getClassLoader()
                .getResourceAsStream("recover.password.api_ru.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл recover.password.api_ru.properties не найден в src/test/resources");
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

    RecoverPassApiMessage(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}