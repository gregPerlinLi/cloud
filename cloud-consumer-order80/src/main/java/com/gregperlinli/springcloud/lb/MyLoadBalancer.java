package com.gregperlinli.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gregPerlinLi
 * @since 2022-04-07
 */
@Slf4j
@Component
public class MyLoadBalancer implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while ( !this.atomicInteger.compareAndSet(current, next) );
        log.info("----- Number of visits: {} -----", next);
        return next;
    }
}
