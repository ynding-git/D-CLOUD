package com.ynding.cloud.route.gateway.admin;

import com.ynding.cloud.common.annotation.CustomSwaggerConfig;
import com.ynding.cloud.common.redis.data.annotation.CustomLettuceRedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@CustomSwaggerConfig
@CustomLettuceRedisConfig
@MapperScan("com.ynding.cloud.route.gateway.admin.dao")
public class GatewayAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }
}
