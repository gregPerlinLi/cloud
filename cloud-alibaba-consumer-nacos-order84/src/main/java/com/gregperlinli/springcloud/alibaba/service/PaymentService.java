package com.gregperlinli.springcloud.alibaba.service;

import com.gregperlinli.springcloud.alibaba.service.impl.PaymentFallbackServiceImpl;
import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackServiceImpl.class)
public interface PaymentService {

    /**
     * Payment sql
     *
     * @param id ID
     * @return message with serial-id
     */
    @GetMapping(value = "/payment-sql/{id}")
    CommonResult<Payment> paymentSql(@PathVariable("id") Long id);
}
