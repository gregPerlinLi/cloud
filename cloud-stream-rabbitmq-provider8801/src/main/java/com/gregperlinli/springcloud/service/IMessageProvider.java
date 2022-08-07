package com.gregperlinli.springcloud.service;

/**
 * @author gregPerlinLi
 * @since 2022-05-17
 */
public interface IMessageProvider {
    /**
     * Send message
     * @return message
     */
    String send();
}
