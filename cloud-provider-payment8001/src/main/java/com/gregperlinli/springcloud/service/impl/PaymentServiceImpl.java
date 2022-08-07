package com.gregperlinli.springcloud.service.impl;

import com.gregperlinli.springcloud.dao.PaymentDao;
import com.gregperlinli.springcloud.entities.Payment;
import com.gregperlinli.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
