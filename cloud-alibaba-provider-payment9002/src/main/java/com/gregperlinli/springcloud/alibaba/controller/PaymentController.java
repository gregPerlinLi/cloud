package com.gregperlinli.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregPerlinLi
 * @since 2022-05-18
 */
@RestController
public class PaymentController {

    @Value(value = "${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "Nacos registry, server port: " + serverPort + "\t" + "id" + id;
    }
}
