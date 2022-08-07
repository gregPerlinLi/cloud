package com.gregperlinli.springcloud.service;

import com.gregperlinli.springcloud.service.impl.PaymentHystrixFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gregPerlinLi
 * @since 2022-05-09
 */
@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentHystrixFallbackServiceImpl.class)
public interface PaymentHystrixService {
    /**
     * Normal access, everything is OK
     *
     * @param id ID
     * @return Tread pool name
     */
    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String paymentInfoOk(@PathVariable("id") Integer id);

    /**
     * Timeout access, there is a timeout problem
     *
     * @param id ID
     * @return Tread pool name
     */
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Integer id);
}
