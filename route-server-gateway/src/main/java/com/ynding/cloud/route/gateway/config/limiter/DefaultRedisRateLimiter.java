package com.ynding.cloud.route.gateway.config.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.support.ConfigurationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import reactor.core.publisher.Mono;

/**
 * @author ynding
 */
@Configuration
public class DefaultRedisRateLimiter extends RedisRateLimiter {

    Config getDefaultConfig() {
        return super.getConfig().get("defaultFilters");
    }

    public DefaultRedisRateLimiter(ReactiveStringRedisTemplate reactiveRedisTemplate,
                                   RedisScript script,
                                   ConfigurationService configurationService) {
        super(reactiveRedisTemplate, script, configurationService);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        if (null == super.getConfig().get(routeId)) {
            getConfig().put(routeId, getDefaultConfig());
        }
        return super.isAllowed(routeId, id);
    }

}
