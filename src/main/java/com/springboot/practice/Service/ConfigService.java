package com.springboot.practice.Service;

import java.util.Map;

public interface ConfigService {

    /**
     * 获取所有配置项
     *
     * @return
     */
    Map<String, String> getAllConfigs();
}
