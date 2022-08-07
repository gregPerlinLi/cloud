package com.gregperlinli.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gregperlinli.springcloud.alibaba.handler.CustomerBlockHandler;
import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
@RestController
public class RateLimitController {

    @GetMapping(value = "/by-resource")
    @SentinelResource(value = "by-resource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "Successfully restricted the flow test by resource name ...", new Payment(2022L, "serial-001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t Service Unavailable ...");
    }

    @GetMapping(value = "/rate-limit/by-url")
    @SentinelResource(value = "by-url")
    public CommonResult byUrl() {
        return new CommonResult(200, "Successfully restricted the flow test by url ...", new Payment(2022L, "serial-002"));
    }

    /*
        CustomerBlockHandler
     */

    @GetMapping(value = "/rate-limit/customer-block-handler")
    @SentinelResource(value = "customer-block-handler",
                        blockHandlerClass = CustomerBlockHandler.class,
                        blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "Successfully restricted the flow test by custom ...", new Payment(2022L, "serial-003"));
    }
}
