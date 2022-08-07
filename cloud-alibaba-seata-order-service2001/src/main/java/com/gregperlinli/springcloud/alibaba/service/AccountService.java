package com.gregperlinli.springcloud.alibaba.service;

import com.gregperlinli.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
@FeignClient("seata-account-service")
public interface AccountService {
    /**
     * Decrease money
     *
     * @param userId User id
     * @param count Count
     * @return
     */
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
