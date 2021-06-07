package org.skillbox.springbootrest.service;

import com.github.cage.Cage;
import com.github.cage.GCage;
import org.skillbox.springbootrest.api.response.CaptchaResponse;
import org.skillbox.springbootrest.api.response.TagsResponse;
import org.skillbox.springbootrest.model.CaptchaCode;
import org.skillbox.springbootrest.repository.CaptchaCodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

@Service
public class CaptchaCodeService {

    @Value("${general.expiredPeriod}")
    private long expiredPeriod;

    private final CaptchaCodeRepository captchaCodeRepository;

    public CaptchaCodeService(CaptchaCodeRepository captchaCodeRepository) {
        this.captchaCodeRepository = captchaCodeRepository;
    }

    public ResponseEntity<CaptchaResponse> updateCaptcha() throws IOException {
        List<CaptchaCode> captchaCodes = captchaCodeRepository.findAll();
        Timestamp currentTime = Timestamp.from(Instant.now());
        for (CaptchaCode code : captchaCodes) {
            if (currentTime.getTime() - code.getTime().getTime() > expiredPeriod) {
                captchaCodeRepository.deleteById(code.getId());
            }
        }
        final Cage cage = new GCage();
        String base64;
        String captchaToken = cage.getTokenGenerator().next();
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            cage.draw(captchaToken, os);
            base64 = "data:image/png;base64, " + Base64.getEncoder().encodeToString(os.toByteArray());
        }
        String captchaSecretCode = String.valueOf(captchaToken.hashCode());
        CaptchaCode captchaCode = new CaptchaCode(currentTime, captchaToken, captchaSecretCode);
        captchaCodeRepository.save(captchaCode);
        return new ResponseEntity<>(new CaptchaResponse(captchaSecretCode, base64), HttpStatus.OK);
    }
}