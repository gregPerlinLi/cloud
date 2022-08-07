package com.gregperlinli.springcloud.dao;

import com.gregperlinli.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
@Mapper
public interface PaymentDao {
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
    Payment getPaymentById(@Param("id") Long id);
}
