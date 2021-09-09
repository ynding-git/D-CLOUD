package com.ynding.cloud.route.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p> 分布式所</p>
 *
 * @author ynding
 * @version 2021/8/24
 **/
@Component
public class DistributedLock {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获得锁
     * setIfAbsent方法，就是当键不存在的时候，设置，并且该方法可以设置键的过期时间.
     * SET lockId content PX millisecond NX
     */
    public boolean getLock(String lockId, long millisecond) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockId, "lock",
                millisecond, TimeUnit.MILLISECONDS);
        return success != null && success;
    }

    /**
     * 释放锁
     *
     * @param lockId
     */
    public void releaseLock(String lockId) {
        redisTemplate.delete(lockId);
    }
}
