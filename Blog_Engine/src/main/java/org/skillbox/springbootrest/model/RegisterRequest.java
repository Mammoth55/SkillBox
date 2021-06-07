package org.skillbox.springbootrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class RegisterRequest {

    @Email
    @JsonProperty("e_mail")
    private String eMail;

    @Size(min = 1, max = 99, message = "Password size should be between 1 and 99 characters !")
    private String password;

    @Size(min = 1, max = 99, message = "Name size should be between 1 and 99 characters !")
    private String name;

    @Size(min = 1, max = 15, message = "Captcha size should be between 1 and 12 characters !")
    private String captcha;

    @NotNull
    @NotBlank
    @JsonProperty("captcha_secret")
    private String captchaSecret;

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getCaptchaSecret() {
        return captchaSecret;
    }
}