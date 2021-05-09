package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.CaptchaCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptchaCodeRepository extends JpaRepository<CaptchaCode, Integer> {
}