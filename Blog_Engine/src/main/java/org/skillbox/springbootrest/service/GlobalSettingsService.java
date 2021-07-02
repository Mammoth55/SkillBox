package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.api.response.GlobalSettingsResponse;
import org.skillbox.springbootrest.model.GlobalSetting;
import org.skillbox.springbootrest.repository.GlobalSettingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalSettingsService {

    @Value("${general.multiUserMode}")
    private String multiUserMode;

    @Value("${general.postPremoderation}")
    private String postPremoderation;

    @Value("${general.statisticsIsPublic}")
    private String statisticsIsPublic;

    private final GlobalSettingRepository globalSettingRepository;

    public GlobalSettingsService(GlobalSettingRepository globalSettingRepository) {
        this.globalSettingRepository = globalSettingRepository;
    }

    public GlobalSettingsResponse getGlobalSettings() {
        List<GlobalSetting> globalSettings = globalSettingRepository.findAll();
        GlobalSettingsResponse globalSettingsResponse = new GlobalSettingsResponse();
        for (GlobalSetting setting : globalSettings) {
            switch (setting.getCode()) {
                case "MULTIUSER_MODE":
                    globalSettingsResponse.setMultiUserMode(setting.getValue().equals("YES"));
                    break;
                case "POST_PREMODERATION":
                    globalSettingsResponse.setPostPreModeration(setting.getValue().equals("YES"));
                    break;
                case "STATISTICS_IS_PUBLIC":
                    globalSettingsResponse.setStatisticsIsPublic(setting.getValue().equals("YES"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Global Setting : " + setting.getCode());
            }
        }
        return globalSettingsResponse;
    }

    public void initGlobalSettings() {
        List<GlobalSetting> globalSettings = globalSettingRepository.findAll();
        for (GlobalSetting setting : globalSettings) {
            switch (setting.getCode()) {
                case "MULTIUSER_MODE":
                    setting.setValue(multiUserMode);
                    globalSettingRepository.save(setting);
                    break;
                case "POST_PREMODERATION":
                    setting.setValue(postPremoderation);
                    globalSettingRepository.save(setting);
                    break;
                case "STATISTICS_IS_PUBLIC":
                    setting.setValue(statisticsIsPublic);
                    globalSettingRepository.save(setting);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Global Setting : " + setting.getCode());
            }
        }
    }
}