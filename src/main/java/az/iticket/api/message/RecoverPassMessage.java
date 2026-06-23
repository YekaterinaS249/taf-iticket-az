package az.iticket.api.message;

public class RecoverPassMessage {
    public static final String EMPTY_EMAIL_ERROR_MESSAGE = "Поле e-mail адрес обязательно для заполнения.";
    public static final String RESET_LINK_SUCCESS_MESSAGE = "Если аккаунт с указанным адресом электронной почты существует, ссылка для сброса пароля была отправлена.";
    public static final String INVALID_FORMAT_EMAIL_MESSAGE = "Поле e-mail адрес должно быть действительным электронным адресом.";
    public static final String MAX_LENGTH_EMAIL_MESSAGE = "Количество символов в поле e-mail адрес не может превышать 255.";
}
