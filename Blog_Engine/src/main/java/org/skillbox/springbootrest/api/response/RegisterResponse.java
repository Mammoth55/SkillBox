package org.skillbox.springbootrest.api.response;

import java.util.HashMap;
import java.util.Map;

public class RegisterResponse {

    private boolean result;
    private final Map<String, String> errors = new HashMap<>();

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