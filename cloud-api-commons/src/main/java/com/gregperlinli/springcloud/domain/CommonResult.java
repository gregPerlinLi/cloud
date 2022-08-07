package com.gregperlinli.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
