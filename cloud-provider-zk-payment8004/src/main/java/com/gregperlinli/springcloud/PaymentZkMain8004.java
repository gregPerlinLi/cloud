package com.gregperlinli.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gregPerlinLi
 * @since 2022-03-31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentZkMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZkMain8004.class, args);
    }
}
