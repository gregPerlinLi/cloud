package com.gregperlinli.springcloud.service.impl;

import com.gregperlinli.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author gregPerlinLi
 * @since 2022-05-17
 */
@Slf4j
@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {

    /**
     * Message sending pipeline
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("---- Serial: {}", serial);
        return serial;
    }
}
