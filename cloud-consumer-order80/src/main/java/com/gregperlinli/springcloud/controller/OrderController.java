package com.gregperlinli.springcloud.controller;

import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import com.gregperlinli.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
@Slf4j
@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://127.0.0.1:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("Got payment to create...");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("Got id to query payment...");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get-for-entity/{id}")
    public CommonResult<Payment> getPaymentForEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if ( entity.getStatusCode().is2xxSuccessful() ) {
            log.info("Status code: {}\t Headers: {}",entity.getStatusCode(), entity.getHeaders());
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "Operation failure!");
        }
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLoadBalance() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if ( instances == null || instances.size() <= 0 ) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    /*
        Zipkin + Sleuth
     */

    @GetMapping(value = "/consumer/payment/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject("http://127.0.0.1:8001" + "/payment/zipkin/", String.class);
    }
}
