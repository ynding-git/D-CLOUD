package com.ynding.cloud.route.gateway;

import com.ynding.cloud.common.annotation.CustomRedisConfig;
import com.ynding.cloud.common.redis.lettuce.annotation.CustomRedisConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p> </p>
 *
 * @author ynding
 * @version 2021/6/10
 **/
@SpringBootApplication
@EnableDiscoveryClient
@CustomRedisConnectionFactory
@EnableFeignClients(basePackages = "com.ynding.cloud.auth.api.authentication.client")
public class RouteServerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteServerGatewayApplication.class, args);
    }
}
