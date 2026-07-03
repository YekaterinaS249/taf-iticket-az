package az.iticket.api.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum LoginApiMessageAz {

    INVALID_CREDENTIALS("auth.invalid.credentials"),
    PASSWORD_REQUIRED("password.required");

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = LoginApiMessageAz.class.getClassLoader()
                .getResourceAsStream("login.api_az.properties")) {

            if (input == null) {
                throw new RuntimeException("Properties file not found: LoginAPITest_az.properties");
            }

            try (InputStreamReader reader =
                         new InputStreamReader(input, StandardCharsets.UTF_8)) {
                PROPERTIES.load(reader);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load login.api_az.properties", e);
        }
    }

    private final String key;

    LoginApiMessageAz(String key) {
        this.key = key;
    }

    public String getMessage() {
        return PROPERTIES.getProperty(key);
    }
}