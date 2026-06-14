package az.iticket.message;

public class LoginMessage {
    public static final String ERROR_EMPTY_EMAIL = "Поле e-mail адрес обязательно для заполнения.";
    public static final String ERROR_EMPTY_PASSWORD = "Поле пароль обязательно для заполнения.";
    public static final String INVALID_EMAIL_FORMAT = "Поле e-mail адрес должно быть действительным электронным адресом.";
    public static final String PASSWORD_TOO_SHORT = "Количество символов в поле пароль должно быть не меньше 8.";
    public static final String INVALID_CREDENTIALS = "The provided credentials do not match our records.";
    public static final String LOGIN_SUCCESS_MESSAGE = "Вы вошли в систему";
}
