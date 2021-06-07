package org.skillbox.springbootrest.api.response;

import java.util.HashMap;
import java.util.Map;

public class RegisterResponse {

    private boolean result;

    private final Map<String, String> errors = new HashMap<>();
//    private final Map<String, String> errors = Map.of("email", "Этот e-mail уже зарегистрирован",
//            "name", "Имя указано неверно",
//            "password", "Пароль короче 6-ти символов",
//            );

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}