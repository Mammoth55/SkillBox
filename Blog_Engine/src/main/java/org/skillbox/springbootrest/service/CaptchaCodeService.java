package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.repository.CaptchaCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class CaptchaCodeService {

    private final CaptchaCodeRepository captchaCodeRepository;

    public CaptchaCodeService(CaptchaCodeRepository captchaCodeRepository) {
        this.captchaCodeRepository = captchaCodeRepository;
    }


}