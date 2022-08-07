package com.gregperlinli.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.gregperlinli.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author gregPerlinLi
 * @since 2022-05-09
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfoOk(Integer id) {
        return "Thread pool: " + Thread.currentThread().getName() + "\tpaymentInfoOk, id: " + id + "\t" + "o(^▽^)o";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",
                    commandProperties = {
                        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
                    })
    public String paymentInfoTimeout(Integer id) {
        // int age = 10/0;
        int sleepTime = 3000;
        // Pause the thread for a few seconds
        try { TimeUnit.MILLISECONDS.sleep(sleepTime); } catch ( InterruptedException e ) { e.printStackTrace(); }
        return "Thread pool: " + Thread.currentThread().getName() + "\tpaymentInfoTimeout, id: " + id + "\t" + "(;￣O￣)" + "\tCost(second): " + sleepTime;
    }

    @Override
    public String paymentInfoTimeoutHandler(Integer id) {
        return "The system 8001 is busy or have an exception, please try again later, current thread name: " + Thread.currentThread().getName() + "\t" + "(╥﹏╥)";
    }

    /*
        ====== Service Break ======
     */

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",
                    commandProperties = {
                            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  // Is the circuit breaker on
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // Number of requests threshold
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   // Time window period
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")    // Failure rate breaking threshold
                    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if ( id < 0 ) {
            throw new RuntimeException("---- ID cannot be negative!");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "Call success, serial number: " + serialNumber;
    }
    @Override
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "The ID cannot be negative, please try again, /(T_T)/~~ \t id: " + id;
    }
}
