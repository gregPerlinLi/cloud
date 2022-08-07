package com.gregperlinli.springcloud.alibaba.dao;

import com.gregperlinli.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
@Mapper
public interface OrderDao {
    /**
     * 1. Create order
     *
     * @param order Order to create
     */
    void create(Order order);

    /**
     * 2. Modify order status, from 0 to 1
     *
     * @param orderId User id
     * @param status Status (0 or 1)
     */
    void update(@Param("orderId") Long orderId, @Param("status") Integer status);
}
