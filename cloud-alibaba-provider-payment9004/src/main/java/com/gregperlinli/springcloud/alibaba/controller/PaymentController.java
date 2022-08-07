package com.gregperlinli.springcloud.alibaba.controller;

import com.gregperlinli.springcloud.entities.CommonResult;
import com.gregperlinli.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
@RestController
public class PaymentController {
    @Value(value = "${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>(3);
    static {
        hashMap.put(1L, new Payment(1L, "28sfg2b28g128458291212bg81298115919198b"));
        hashMap.put(2L, new Payment(2L, "12ds1h9g1f51mk5j9hf1gk9581s5b1df1h8gf1f"));
        hashMap.put(3L, new Payment(3L, "959dg5a9h49gf49vds9a14gh9fda919a54d9g41"));
    }

    @GetMapping(value = "/payment-sql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200, "from mysql, server port: " + serverPort, payment);
    }
}
