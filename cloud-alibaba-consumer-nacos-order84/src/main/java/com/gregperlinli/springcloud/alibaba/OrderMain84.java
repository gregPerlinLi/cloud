package com.gregperlinli.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class, args);
    }
}
