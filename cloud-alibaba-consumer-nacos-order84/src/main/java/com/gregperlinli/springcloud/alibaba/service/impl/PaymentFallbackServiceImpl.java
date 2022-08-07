package com.gregperlinli.springcloud.alibaba.service.impl;

import com.gregperlinli.springcloud.alibaba.service.PaymentService;
import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
@Service
public class PaymentFallbackServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSql(Long id) {
        return new CommonResult<>(444, "Service degradation return ==> PaymentFallbackServiceImpl ...", new Payment(id, "ERROR_SERIAL"));
    }
}
