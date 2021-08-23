package com.ynding.cloud.route.gateway.config.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义限流标志的key，多个维度可以从这里入手
 * exchange对象中获取服务ID、请求信息，用户信息等
 * @author ynding
 */
@Component
public class RequestRateLimiterConfig {

    /**
     * ip地址限流
     *
     * @return 限流key
     */
    @Bean
    public KeyResolver remoteAddressKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 请求路径限流
     *
     * @return 限流key
     */
    @Bean
    @Primary
    public KeyResolver apiKeyResolver() {
        //按URL限流,即以每秒内请求数按URL分组统计，超出限流的url请求都将返回429状态
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * username限流
     *
     * @return 限流key
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }
}
