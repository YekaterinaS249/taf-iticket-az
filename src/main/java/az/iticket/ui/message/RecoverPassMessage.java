package az.iticket.ui.message;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum RecoverPassMessage {
    RESET_LINK_SENT("reset.link.success"),
    EMAIL_REQUIRED("email.required"),
    INVALID_EMAIL("email.invalid"),
    INVALID_EMAIL_FORMAT("email.invalid.format"),
    MAX_EMAIL_LENGTH("email.max.length"),
    RESET_PASSWORD("reset.password.message");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = RecoverPassMessage.class
                .getClassLoader()
                .getResourceAsStream("recover.password.ui_ru.properties")) {

            if (input == null) {
                throw new RuntimeException("Properties file not found: recover.password.ui_ru.properties");
            }

            try (InputStreamReader reader =
                         new InputStreamReader(input, StandardCharsets.UTF_8)) {
                PROPERTIES.load(reader);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    private final String key;

    RecoverPassMessage(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}