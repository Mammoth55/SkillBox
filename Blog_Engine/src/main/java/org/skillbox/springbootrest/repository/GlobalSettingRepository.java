package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.GlobalSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalSettingRepository extends JpaRepository<GlobalSetting, Integer> {
}