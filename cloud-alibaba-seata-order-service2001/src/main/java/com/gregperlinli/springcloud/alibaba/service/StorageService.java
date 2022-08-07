package com.gregperlinli.springcloud.alibaba.service;

import com.gregperlinli.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gregPerlinLi
 * @since 2022-05-25
 */
@FeignClient("seata-storage-service")
public interface StorageService {
    /**
     * Decrease storage
     *
     * @param productId Product id
     * @param count Count
     * @return Return common result
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
