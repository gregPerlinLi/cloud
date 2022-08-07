package com.gregperlinli.springcloud.service;

import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gregPerlinLi
 * @since 2022-05-09
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * Get payment by id
     *
     * @param id id to get
     * @return the payment with common result
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * Feign timeout test
     *
     * @return service port
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
