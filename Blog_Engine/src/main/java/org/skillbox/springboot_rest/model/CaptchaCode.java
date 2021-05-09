package org.skillbox.springboot_rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "captcha_codes")
public class CaptchaCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "code")
    private String code;

    @JsonProperty("secret_code")
    private String secretCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }
}