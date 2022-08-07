package com.gregperlinli.springcloud.controller;

import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import com.gregperlinli.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value(value = "${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create( Payment payment) {
        int result = paymentService.create(payment);
        log.info("---- Insert result: {} ----", result);

        if ( result > 0 ) {
            return new CommonResult(200, "Insert database success! Server port: " + serverPort, result);
        } else {
            return new CommonResult(444, "Insert database failed!", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("---- Get result: {} ----", payment);
        if ( payment != null ) {
            return new CommonResult(200, "Get data success! Sever port: " + serverPort, payment);
        } else {
            return new CommonResult(444, "No corresponding record, query ID: " + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for ( String service : services ) {
            log.info("====== Service: {} ======", service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("====== Instance: {} \t {} \t {} \t {} ======", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLoadBalance() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // Pause the thread for a few seconds
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e ) { e.printStackTrace(); }
        return serverPort;
    }
}
