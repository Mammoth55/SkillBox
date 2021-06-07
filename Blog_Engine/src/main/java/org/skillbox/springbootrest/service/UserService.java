package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.api.response.RegisterResponse;
import org.skillbox.springbootrest.model.CaptchaCode;
import org.skillbox.springbootrest.model.RegisterRequest;
import org.skillbox.springbootrest.model.User;
import org.skillbox.springbootrest.repository.CaptchaCodeRepository;
import org.skillbox.springbootrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Value("${general.passwordMinLength}")
    private int passwordMinLength;

    private final UserRepository userRepository;
    private final CaptchaCodeRepository captchaCodeRepository;

    public UserService(UserRepository userRepository, CaptchaCodeRepository captchaCodeRepository) {
        this.userRepository = userRepository;
        this.captchaCodeRepository = captchaCodeRepository;
    }

    public ResponseEntity<RegisterResponse> register(RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();
        CaptchaCode captchaCode = captchaCodeRepository.findAll().stream().
                filter(e -> e.getSecretCode().equals(registerRequest.getCaptchaSecret())).
                findFirst().orElseThrow();
        if (!captchaCode.getCode().equals(registerRequest.getCaptcha())) {
            response.getErrors().put("captcha", "Код с картинки введён неверно");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (registerRequest.getPassword().length() < passwordMinLength) {
            response.getErrors().put("password", "Пароль короче " + passwordMinLength + " символов !");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (!registerRequest.getName().matches("[A-ZА-ЯЁa-zа-яё0-9_]+")) {
            response.getErrors().put("name", "Имя указано неверно !");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<String> emailList = userRepository.findAll().stream().
                map(User::getEmail).collect(Collectors.toList());
        if (emailList.contains(registerRequest.geteMail())) {
            response.getErrors().put("email", "Этот e-mail уже зарегистрирован !");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setIsModerator(0);
        user.setRegTime(Timestamp.from(Instant.now()));
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.geteMail());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
        response.setResult(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}