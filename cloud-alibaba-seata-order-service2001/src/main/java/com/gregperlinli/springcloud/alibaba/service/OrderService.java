package com.gregperlinli.springcloud.alibaba.service;

import com.gregperlinli.springcloud.alibaba.domain.Order;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
public interface OrderService {
    /**
     * Create order
     *
     * @param order Order
     */
    void create(Order order);


}
