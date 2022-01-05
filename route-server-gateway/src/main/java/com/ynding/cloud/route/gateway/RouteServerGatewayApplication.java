package com.ynding.cloud.route.gateway;

import com.ynding.cloud.common.redis.data.annotation.CustomRedisConnectionFactory;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

import java.util.stream.Collectors;

/**
 * <p> </p>
 *
 * @author ynding
 * @version 2021/6/10
 **/
@SpringBootApplication
@EnableDiscoveryClient
@CustomRedisConnectionFactory
@EnableDubbo
@EnableFeignClients
@Import(BeanValidatorPluginsConfiguration.class)
public class RouteServerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteServerGatewayApplication.class, args);
    }

    /**
     * ribbon/feign restTemplate 等http协议接口， 返回类序列化
     * @param converters
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
