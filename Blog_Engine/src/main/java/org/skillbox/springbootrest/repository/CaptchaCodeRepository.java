package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.CaptchaCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptchaCodeRepository extends JpaRepository<CaptchaCode, Integer> {
}