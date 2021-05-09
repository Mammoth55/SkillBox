package org.skillbox.springboot_rest.controller;

import org.skillbox.springboot_rest.api.response.AuthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @GetMapping("/check")
    public AuthResponse check() {
        return new AuthResponse();
    }
}