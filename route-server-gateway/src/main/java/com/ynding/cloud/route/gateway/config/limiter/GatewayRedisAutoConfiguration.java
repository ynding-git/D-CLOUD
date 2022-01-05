package com.ynding.cloud.route.gateway.config.limiter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * <p> </p>
 *
 * @author ynding
 * @version 2021/7/26
 **/
@Configuration
@RequiredArgsConstructor
public class GatewayRedisAutoConfiguration {

    @Qualifier("myLettuceConnectionFactory")
    private final LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedisScript redisRequestRateLimiterScript() {
        Resource scriptSource = new ClassPathResource("scripts/gateway.lua");
        return RedisScript.of(scriptSource, List.class);
    }

    @Bean
    public ReactiveRedisConnectionFactory connectionFactory() {
        return lettuceConnectionFactory;
    }

    @Bean
    public ReactiveRedisTemplate<String, String> stringReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        RedisSerializer<String> serializer = new StringRedisSerializer();
        RedisSerializationContext<String, String> serializationContext = RedisSerializationContext
                .<String, String>newSerializationContext()
                .key(serializer).value(serializer)
                .hashKey(serializer).hashValue(serializer)
                .build();
        return new ReactiveRedisTemplate(connectionFactory, serializationContext);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisRateLimiter redisRateLimiter(ReactiveStringRedisTemplate redisTemplate,
                                             @Qualifier(RedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> redisScript,
                                             ConfigurationService configurationService) {
        return new DefaultRedisRateLimiter(redisTemplate, redisScript, configurationService);
    }
}
