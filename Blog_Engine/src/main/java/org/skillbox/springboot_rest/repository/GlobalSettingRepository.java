package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.GlobalSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalSettingRepository extends JpaRepository<GlobalSetting, Integer> {
}