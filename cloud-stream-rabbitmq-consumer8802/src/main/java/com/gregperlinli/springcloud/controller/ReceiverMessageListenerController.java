package com.gregperlinli.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * @author gregPerlinLi
 * @since 2022-05-17
 */
@Slf4j
@Controller
@EnableBinding(Sink.class)
public class ReceiverMessageListenerController {
    @Value(value = "${server.port}")
    private String serverPort;

    @StreamListener(value = Sink.INPUT)
    public void input(Message<String> message) {
        log.info("Consumer 1, ---> Received message: {} \t port: {}", message.getPayload(), serverPort);
    }
}
