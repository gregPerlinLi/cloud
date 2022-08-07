package com.gregperlinli.springcloud.service;

import com.gregperlinli.springcloud.entities.Payment;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
public interface PaymentService {
    /**
     * Create payment
     *
     * @param payment payment
     * @return state
     */
    int create(Payment payment);

    /**
     * Read payment
     *
     * @param id payment id
     * @return payment
     */
    Payment getPaymentById(Long id);
}
