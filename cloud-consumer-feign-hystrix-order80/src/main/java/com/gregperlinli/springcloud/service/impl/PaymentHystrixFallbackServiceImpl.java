package com.gregperlinli.springcloud.service.impl;

import com.gregperlinli.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

/**
 * @author gregPerlinLi
 * @since 2022-05-10
 */
@Service
public class PaymentHystrixFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "---- PaymentFallbackService fall back - paymentInfoOk, " + "(╥﹏╥)";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "---- PaymentFallbaclService fall back - paymentInfoTimeout" + "(╥﹏╥)";
    }
}
