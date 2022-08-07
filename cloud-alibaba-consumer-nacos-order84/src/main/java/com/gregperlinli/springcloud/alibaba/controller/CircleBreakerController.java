package com.gregperlinli.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gregperlinli.springcloud.alibaba.service.PaymentService;
import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
@RestController
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private PaymentService paymentService;

    @RequestMapping(value = "/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback")
    // @SentinelResource(value = "fallback", fallback = "handlerFallback")
    // @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallback",
                        blockHandler = "blockHandler",
                        exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payment-sql/" + id, CommonResult.class, id);

        if ( id == 4 ) {
            throw new IllegalArgumentException("IllegalArgumentException ...");
        } else if ( result.getData() == null ) {
            throw new NullPointerException("NullPointerException, The ID does not have a corresponding record ...");
        }

        return result;
    }

    /**
     * Fallback
     */
    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable exception) {
        return new CommonResult<>(444, "THERE IS A RUNTIME EXCEPTION! handlerFallback, exception: " + exception.getMessage(), new Payment(id, "NULL"));
    }

    /**
     * Block Handler
     */
    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException exception) {
        return new CommonResult<>(445, "THERE IS A FLOW LIMITED EXCEPTION! blockHandler, exception: " + exception.getMessage(), new Payment(id, "NULL"));
    }

    /*
        Open Feign
     */

    @GetMapping(value = "/consumer/payment-sql/{id}")
    CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        return paymentService.paymentSql(id);
    }
}
