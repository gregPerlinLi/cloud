package com.gregperlinli.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author gregPerlinLi
 * @since 2022-04-07
 */
public interface LoadBalancer {
    /**
     * Load balancing algorithm: <br/>
     * {@code the number of requests of the rest interface % the number of server clusters = the subscript of the actual calling server location}.<br/>
     * The count of the rest interface starts from 1 after each service restart.
     *
     * @param serviceInstances service instances
     * @return LoadBalanced instance
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
