package com.gregperlinli.springcloud.alibaba.controller;

import com.gregperlinli.springcloud.alibaba.service.OrderService;
import com.gregperlinli.springcloud.alibaba.domain.CommonResult;
import com.gregperlinli.springcloud.alibaba.domain.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping(value = "/")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "Order created successfully ...");
    }
}
