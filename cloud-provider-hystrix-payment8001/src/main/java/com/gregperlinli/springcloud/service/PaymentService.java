package com.gregperlinli.springcloud.service;



/**
 * @author gregPerlinLi
 * @since 2022-05-09
 */
public interface PaymentService {

    /**
     * Normal access, everything is OK
     *
     * @param id ID
     * @return Tread pool name
     */
    String paymentInfoOk(Integer id);

    /**
     * Timeout access, there is a timeout problem
     *
     * @param id ID
     * @return Tread pool name
     */
    String paymentInfoTimeout(Integer id);

    /**
     * Timeout access failed handler
     *
     * @param id ID
     * @return Exception message and tread pool name
     */
    String paymentInfoTimeoutHandler(Integer id);

    /**
     * Payment Breaker
     *
     * @param id id
     * @return Serial id message
     */
    String paymentCircuitBreaker(Integer id);

    /**
     * Payment breaker fallback
     *
     * @param id
     * @return Break info
     */
    String paymentCircuitBreakerFallback(Integer id);
}
