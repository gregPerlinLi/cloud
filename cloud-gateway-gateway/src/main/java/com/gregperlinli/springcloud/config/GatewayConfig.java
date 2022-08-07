package com.gregperlinli.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gregPerlinLi
 * @since 2022-05-11
 */
@Configuration
public class GatewayConfig {
    /**
     * A routing rule with ID of path_route_baidu_news is configured. <br/>
     * When the access address is <a href="http://127.0.0.1:9527/guonei">http://127.0.0.1:9527/guonei</a>, it will be automatically forwarded to the address <a href="http://news.baidu.com/guonei">http://news.baidu.com/guonei</a>
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_baidu_news",
                        route -> route.path("/guonei")
                                        .uri("http://news.baidu.com/guonei"));
        return routes.build();
    }
}
