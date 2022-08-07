package com.gregperlinli.springcloud.controller;

import com.gregperlinli.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gregPerlinLi
 * @since 2022-05-17
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/send-message")
    public String sendMessage() {
        return iMessageProvider.send();
    }
}
