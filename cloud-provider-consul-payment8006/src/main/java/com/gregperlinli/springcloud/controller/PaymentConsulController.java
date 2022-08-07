package com.gregperlinli.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author gregPerlinLi
 * @since 2022-03-31
 */
@Slf4j
@RestController
public class PaymentConsulController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul() {
        return "SpringCloud with consul: " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
