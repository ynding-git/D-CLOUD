package com.ynding.cloud.route.gateway.admin;

import com.ynding.cloud.common.redis.data.annotation.CustomLettuceRedisDataConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@CustomLettuceRedisDataConfig
@MapperScan("com.ynding.cloud.route.gateway.admin.dao")
public class GatewayAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }
}
