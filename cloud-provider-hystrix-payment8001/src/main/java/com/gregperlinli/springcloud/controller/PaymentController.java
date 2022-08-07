package com.gregperlinli.springcloud.controller;

import com.gregperlinli.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @since 2022-05-09
 */
@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value(value = "${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoOk(id);
        log.info("---- Result: {} ----", result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoTimeout(id);
        log.info("---- Result: {} ----", result);
        return result;
    }

    /*
        ====== Service Break ======
     */

    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("---- Result: {}", result);
        return result;
    }
}

