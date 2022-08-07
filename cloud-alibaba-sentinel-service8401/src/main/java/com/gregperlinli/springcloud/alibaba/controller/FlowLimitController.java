package com.gregperlinli.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author gregPerlinLi
 * @since 2022-05-23
 */
@Slf4j
@RestController
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA() {
        log.info("{} \t ---- Test A ...", Thread.currentThread().getName());
        return "---- This is test A ...";
    }

    @GetMapping(value = "/testB")
    public String testB() {
        return "---- This is test B ...";
    }

    @GetMapping(value = "/testD")
    public String testD() {
        log.info("---- This is test D testing exception rate ...");
        int age = 10 / 0;
        return "---- This is test D ...";
    }

    @GetMapping(value = "/testE")
    public String testE() {
        log.info("---- This is test E testing exception times within a period of time ...");
        int age = 10 / 0;
        return "---- This is test E ...";
    }

    @GetMapping(value = "/test-hot-key/{p1}/{p2}")
    @SentinelResource(value = "test-hot-key", blockHandler = "dealTestHotKey")
    public String testHotKey(@PathVariable(value = "p1", required = false) String p1,
                              @PathVariable(value = "p2", required = false) String p2) {
        return "---- This is testing hot key ...";
    }

    public String dealTestHotKey(String p1, String p2, BlockException exception) {
        // Sentinel default caution: Blocked by Sentinel (flow limiting)
        return "---- This is testing hot key with block exception o(╥﹏╥)o ...";
    }
}
