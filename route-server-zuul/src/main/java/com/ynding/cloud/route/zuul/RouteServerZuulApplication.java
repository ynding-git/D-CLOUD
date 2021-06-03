package com.ynding.cloud.route.zuul;

import com.ynding.cloud.common.annotation.CustomSwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author ynding
 * @version 2020/09/30
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@CustomSwaggerConfig
public class RouteServerZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouteServerZuulApplication.class, args);
    }

}
