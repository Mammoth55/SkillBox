package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @GetMapping("/check")
    public ResponseEntity<AuthResponse> check() {
        return new ResponseEntity<>(new AuthResponse(), HttpStatus.OK);
    }
}