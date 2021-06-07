package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.AuthResponse;
import org.skillbox.springbootrest.api.response.CaptchaResponse;
import org.skillbox.springbootrest.api.response.RegisterResponse;
import org.skillbox.springbootrest.model.RegisterRequest;
import org.skillbox.springbootrest.service.CaptchaCodeService;
import org.skillbox.springbootrest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final CaptchaCodeService captchaCodeService;
    private final UserService userService;

    public ApiAuthController(CaptchaCodeService captchaCodeService, UserService userService) {
        this.captchaCodeService = captchaCodeService;
        this.userService = userService;
    }

    @GetMapping("/check")
    public ResponseEntity<AuthResponse> check() {
        return new ResponseEntity<>(new AuthResponse(), HttpStatus.OK);
    }

    @GetMapping("/captcha")
    public ResponseEntity<CaptchaResponse> captcha() throws IOException {
        return captchaCodeService.updateCaptcha();
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}