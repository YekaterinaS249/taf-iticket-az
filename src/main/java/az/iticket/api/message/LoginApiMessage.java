package az.iticket.api.message;

public class LoginApiMessage {
    public static final String PASSWORD_REQUIRED_MESSAGE = "Поле пароль обязательно для заполнения.";
    public static final String EMAIL_REQUIRED_MESSAGE = "Поле e-mail адрес обязательно для заполнения.";
    public static final String AUTH_INVALID_CREDENTIALS = "The provided credentials do not match our records.";
    public static final String INVALID_EMAIL_FORMAT_MESSAGE = "Поле e-mail адрес должно быть действительным электронным адресом.";
    public static final String MAX_LENGTH_EMAIL_MESSAGE = "Количество символов в поле e-mail адрес не может превышать 255.";
    public static final String MIN_LENGTH_PASSWORD_MESSAGE = "Количество символов в поле пароль должно быть не меньше 8.";
    public static final String MAX_LENGTH_PASSWORD_MESSAGE = "Количество символов в поле пароль не может превышать 255.";

}
