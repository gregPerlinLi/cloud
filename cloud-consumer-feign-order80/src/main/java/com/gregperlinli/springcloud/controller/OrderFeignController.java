package com.gregperlinli.springcloud.controller;

import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import com.gregperlinli.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
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
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }


    @GetMapping(value = "/consumer/payment/feign/timeout")
    String paymentFeignTimeout() {
        // The default wait of OpenFeign client  is 1 second
        return paymentFeignService.paymentFeignTimeout();
    }
}
