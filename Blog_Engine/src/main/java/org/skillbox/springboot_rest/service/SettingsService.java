package org.skillbox.springboot_rest.service;

import org.skillbox.springboot_rest.api.response.SettingsResponse;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    public SettingsResponse getGlobalSettings() {
        SettingsResponse settingsResponse = new SettingsResponse();
        settingsResponse.setPostPreModeration(true);
        settingsResponse.setStatisticsIsPublic(true);
        return settingsResponse;
    }
}