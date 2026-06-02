package az.iticket.api.endpoints;

public class Endpoints {
        public static final String BASE_URL =
                "https://api.iticket.az/ru";

        public static final String LOGIN =
                BASE_URL + "/v5/user/auth/token";

        public static final String RECOVER_PASSWORD =
                BASE_URL + "/v5/user/auth/forgot-password";
    }

