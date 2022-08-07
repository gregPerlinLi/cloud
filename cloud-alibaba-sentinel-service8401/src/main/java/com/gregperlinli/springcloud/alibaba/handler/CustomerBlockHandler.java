package com.gregperlinli.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gregperlinli.springcloud.entities.CommonResult;

/**
 * @author gregPerlinLi
 * @since 2022-05-24
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return  new CommonResult(2020, "Custom flow limit processing information globally ==> CustomerBlockHandler ...");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return  new CommonResult(2020, "Custom flow limit processing information globally ==> CustomerBlockHandler2...");
    }
}
