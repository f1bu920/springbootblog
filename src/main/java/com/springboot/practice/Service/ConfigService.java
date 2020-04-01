package com.springboot.practice.Service;

import java.util.Map;

public interface ConfigService {

    /**
     * 获取所有配置项
     *
     * @return
     */
    Map<String, String> getAllConfigs();

    /**
     * 修改配置项
     *
     * @param configName
     * @param configValue
     * @return
     */
    int updateConfig(String configName, String configValue);
}
