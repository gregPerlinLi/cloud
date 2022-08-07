package com.gregperlinli.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregPerlinLi
 * @since 2022-05-18
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value(value = "${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String getConfigInfo() {
        return "Config info: " + configInfo;
    }
}
